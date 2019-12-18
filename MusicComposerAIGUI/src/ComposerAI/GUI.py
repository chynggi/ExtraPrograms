# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

import wx
import os
import wx.xrc
import wx.stc
from ComposerAI import MakeModule
from ComposerAI import MusicComposerAI
from ComposerAI import ExtraFunctions


###########################################################################
## Class MyFrame3
###########################################################################




class MyFrame3 ( wx.Frame ):
    
    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = u"Music Composer", pos = wx.DefaultPosition, size = wx.Size( 647,582 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
        
        self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
        
        bSizer3 = wx.BoxSizer( wx.VERTICAL )
        
        sbSizer1 = wx.StaticBoxSizer( wx.StaticBox( self, wx.ID_ANY, u"모델 제작하기" ), wx.VERTICAL )
        
        self.m_panel1 = wx.Panel( sbSizer1.GetStaticBox(), wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        gSizer1 = wx.GridSizer( 0, 3, 0, 0 )
        
        self.m_staticText4 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"Midi Files Path", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText4.Wrap( -1 )
        gSizer1.Add( self.m_staticText4, 0, wx.ALL, 5 )
        
        self.m_dirPicker4 = wx.DirPickerCtrl( self.m_panel1, wx.ID_ANY, wx.EmptyString, u"Select a folder", wx.DefaultPosition, wx.DefaultSize, wx.DIRP_DEFAULT_STYLE )
        gSizer1.Add( self.m_dirPicker4, 0, wx.ALL, 5 )
        
        self.m_button5 = wx.Button( self.m_panel1, wx.ID_ANY, u"CountFile", wx.DefaultPosition, wx.DefaultSize, 0 )
        gSizer1.Add( self.m_button5, 0, wx.ALL, 5 )
        
        self.m_staticText5 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"Model Save Path", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText5.Wrap( -1 )
        gSizer1.Add( self.m_staticText5, 0, wx.ALL, 5 )
        
        self.m_filePicker3 = wx.FileDialog(self, "Save to file:", ".", "", "Model (*.h5)|*.h5", wx.FD_SAVE | wx.FD_OVERWRITE_PROMPT)
        gSizer1.Add( self.m_filePicker3, 0, wx.ALL, 5 )
        
        self.m_button6 = wx.Button( self.m_panel1, wx.ID_ANY, u"Generate", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_button6.SetDefault() 
        gSizer1.Add( self.m_button6, 0, wx.ALL, 5 )
        
        
        self.m_panel1.SetSizer( gSizer1 )
        self.m_panel1.Layout()
        gSizer1.Fit( self.m_panel1 )
        sbSizer1.Add( self.m_panel1, 1, wx.EXPAND |wx.ALL, 5 )
        
        self.m_staticText10 = wx.StaticText( sbSizer1.GetStaticBox(), wx.ID_ANY, u"Logs", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText10.Wrap( -1 )
        sbSizer1.Add( self.m_staticText10, 0, wx.ALL, 5 )
        
        self.m_textCtrl1 = wx.TextCtrl( sbSizer1.GetStaticBox(), wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 620,150 ), 0 )
        sbSizer1.Add( self.m_textCtrl1, 0, wx.ALL, 5 )
        
        
        bSizer3.Add( sbSizer1, 1, wx.EXPAND, 5 )
        
        sbSizer2 = wx.StaticBoxSizer( wx.StaticBox( self, wx.ID_ANY, u"작곡하기" ), wx.VERTICAL )
        
        self.m_panel3 = wx.Panel( sbSizer2.GetStaticBox(), wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        gSizer11 = wx.GridSizer( 0, 3, 0, 0 )
        
        self.m_staticText41 = wx.StaticText( self.m_panel3, wx.ID_ANY, u"Model Path", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText41.Wrap( -1 )
        gSizer11.Add( self.m_staticText41, 0, wx.ALL, 5 )
        
        self.m_dirPicker41 = wx.FilePickerCtrl( self.m_panel3, wx.ID_ANY, wx.EmptyString, u"Select a Model file", u"*.h5", wx.DefaultPosition, wx.DefaultSize, wx.FLP_DEFAULT_STYLE )
        gSizer11.Add( self.m_dirPicker41, 0, wx.ALL, 5 )
        
        self.m_button51 = wx.Button( self.m_panel3, wx.ID_ANY, u"Vaildate", wx.DefaultPosition, wx.DefaultSize, 0 )
        gSizer11.Add( self.m_button51, 0, wx.ALL, 5 )
        
        self.m_staticText51 = wx.StaticText( self.m_panel3, wx.ID_ANY, u"Composed Midi File Save Path", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText51.Wrap( -1 )
        gSizer11.Add( self.m_staticText51, 0, wx.ALL, 5 )
        
        self.m_filePicker31 = wx.FileDialog(self, "Save to file:", ".", "", "MIDI (*.mid)|*.mid", wx.FD_SAVE | wx.FD_OVERWRITE_PROMPT)
        gSizer11.Add( self.m_filePicker31, 0, wx.ALL, 5 )
        
        self.m_button61 = wx.Button( self.m_panel3, wx.ID_ANY, u"Compose", wx.DefaultPosition, wx.DefaultSize, 0 )
        gSizer11.Add( self.m_button61, 0, wx.ALL, 5 )
        
        
        self.m_panel3.SetSizer( gSizer11 )
        self.m_panel3.Layout()
        gSizer11.Fit( self.m_panel3 )
        sbSizer2.Add( self.m_panel3, 1, wx.EXPAND |wx.ALL, 5 )
        
        self.m_staticText11 = wx.StaticText( sbSizer2.GetStaticBox(), wx.ID_ANY, u"Logs", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText11.Wrap( -1 )
        sbSizer2.Add( self.m_staticText11, 0, wx.ALL, 5 )
        
        self.m_textCtrl11 = wx.TextCtrl( sbSizer2.GetStaticBox(), wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 620,150 ), 0 )
        sbSizer2.Add( self.m_textCtrl11, 0, wx.ALL, 5 )
        
        
        bSizer3.Add( sbSizer2, 1, wx.EXPAND, 5 )
        
        
        self.SetSizer( bSizer3 )
        self.Layout()
        
        self.Centre( wx.BOTH )
        
        # Connect Events
        self.m_button5.Bind( wx.EVT_BUTTON, self.m_button5OnButtonClick )
        self.m_button6.Bind( wx.EVT_BUTTON, self.m_button6OnButtonClick )
        self.m_button51.Bind( wx.EVT_BUTTON, self.m_button51OnButtonClick )
        self.m_button61.Bind( wx.EVT_BUTTON, self.m_button61OnButtonClick )
    
    def __del__( self ):
        pass
    
    
    # Virtual event handlers, overide them in your derived class
    def m_button5OnButtonClick( self, event ):
        ExtraFunctions.vaildatePath(self.m_dirPicker4.GetPath(), self.m_textCtrl1.WriteText)
    
    def m_button6OnButtonClick( self, event ):
        self.m_filePicker3.ShowModal()
        midipath = self.m_dirPicker4.GetPath()
        modelpath = self.m_filePicker3.GetFilename()
        MakeModule.MakeModuleandSave(midipath, modelpath, self.m_textCtrl1.WriteText)
    
    def m_button51OnButtonClick( self, event ):
        ExtraFunctions.vaildateModel(self.m_dirPicker41.GetPath(),self.m_textCtrl11.WriteText)
    
    def m_button61OnButtonClick( self, event ):
        self.m_filePicker31.ShowModal()
        modelpath = self.m_dirPicker41.GetPath()
        midipath = self.m_filePicker31.GetFilename()
        MusicComposerAI.MusicComposer(modelpath, midipath,self.m_textCtrl11.WriteText)
    
    

if __name__ == '__main__':
    app = wx.App();
    frame = MyFrame3(parent=None);
    frame.Show(show=True);    
    app.MainLoop();
    
    

    