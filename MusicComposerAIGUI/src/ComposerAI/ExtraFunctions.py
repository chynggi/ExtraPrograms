'''
Created on 2019. 12. 17.

@author: vision
'''
from music21 import converter, instrument, note, chord, midi, stream
from keras.models import load_model
import glob
notes = []
track = 0
def vaildatePath(path,Log):
    for i, file in enumerate(glob.glob(path+"/*.mid")):
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
        Log("Song {} Loaded".format(i+1))
        Log("\n")

def vaildateModel(model,Log):
    try:
        truemodel = load_model(model);
    except:
        Log("load failed")
        pass;
    finally:
        Log("load Success")
    pass;


