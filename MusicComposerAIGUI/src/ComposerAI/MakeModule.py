'''
Created on 2019. 11. 15.

@author: vision
'''
from music21 import converter, instrument, note, chord, midi, stream
import glob
import numpy as np
import matplotlib.pyplot as plt
from keras.utils import np_utils
from keras.models import Sequential
from keras.layers import Dense, Input, LSTM, Dropout, Activation
from keras.callbacks import EarlyStopping, ModelCheckpoint
from keras.models import load_model


# ### Preprocessing
# First we must load the data from the songs. To do this, we'll go through all the songs in our training data of MIDI files. We parse them with music21 to get the individual notes. If the element is a chord, then it is converted to it's numerical representation. After this step we will have all of the notes/chords that appear in string form, and a corresponding vocabulary as a set of them all.

# In[2]:


notes = []
track = 0

for i, file in enumerate(glob.glob("splatoon/*.mid")):
    midi = converter.parse(file)
    # There are multiple tracks in the MIDI file, so we'll use the first one
    midi = midi[track]
    notes_to_parse = None
        
    # Parse the midi file by the notes it contains
    notes_to_parse = midi.flat.notes
        
    for element in notes_to_parse:
        if isinstance(element, note.Note):
            notes.append(str(element.pitch))
        elif isinstance(element, chord.Chord):
            # get's the normal order (numerical representation) of the chord
            notes.append('.'.join(str(n) for n in element.normalOrder))
    print("Song {} Loaded".format(i+1))
                
print("DONE LOADING SONGS")    
# Get all pitch names
pitches = sorted(set(item for item in notes))
print(pitches)
with open("pitches.txt",'w',encoding='utf-8') as f1:
    for i in range(len(pitches)):
        f1.write(pitches[i])
        f1.write("\n");  
# Get all pitch names

vocab_length = len(pitches)  
with open("vocab_length.txt",'w',encoding='utf-8') as f2:
    f2.write(str(vocab_length))
number_notes = len(notes)
with open("number_notes.txt",'w',encoding='utf-8') as f3:
    f3.write(str(number_notes))
    
print(vocab_length)
print(notes)


# Now we must get these notes in a usable form for our LSTM. Let's construct sequences that can be grouped together to predict the next note in groups of 10 notes.

# In[3]:


# Let's use One Hot Encoding for each of the notes and create an array as such of sequences. 
#Let's first assign an index to each of the possible notes
note_dict = dict()
for i, note in enumerate(pitches):
    note_dict[note] = i
#print(note_dict)

# Now let's construct sequences. Taking each note and encoding it as a numpy array with a 1 in the position of the note it has
sequence_length = 50
# Lets make a numpy array with the number of training examples, sequence length, and the length of the one-hot-encoding
num_training = number_notes - sequence_length

input_notes = np.zeros((num_training, sequence_length, vocab_length))
output_notes = np.zeros((num_training, vocab_length))

for i in range(0, num_training):
    # Here, i is the training example, j is the note in the sequence for a specific training example
    input_sequence = notes[i: i+sequence_length]
    output_note = notes[i+sequence_length]
    for j, note in enumerate(input_sequence):
        input_notes[i][j][note_dict[note]] = 1
    output_notes[i][note_dict[output_note]] = 1


# In[4]:


model = Sequential()
model.add(LSTM(128, return_sequences=True, input_shape=(sequence_length, vocab_length)))
model.add(Dropout(0.2))
model.add(LSTM(128, return_sequences=False))
model.add(Dropout(0.2))
model.add(Dense(vocab_length))
model.add(Activation('softmax'))

#model.load_weights('MusicSeq.h5')

model.compile(loss='categorical_crossentropy', optimizer='rmsprop', metrics=['acc'])



history = model.fit(input_notes, output_notes, batch_size=128, nb_epoch=200)


# #### Visualizing the Model's Results
# The models accuracy can be seen here increasing, as it learns the sequences over the course of 200 epochs.

# In[5]:


# summarize history for accuracy
plt.plot(history.history['acc'])
plt.title('model accuracy')
plt.ylabel('accuracy')
plt.xlabel('epoch')
plt.legend(['train'], loc='upper left')
plt.show()


# ### Generating New Music

# In[6]:



model.save('MusicSeq.h5')