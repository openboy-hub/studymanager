package com.graduationproject.studymanager.service;

import com.graduationproject.studymanager.bean.Note;
import com.graduationproject.studymanager.bean.NoteCategory;

import java.util.List;

public interface NoteService {
    public void setNewNote(Note note);
    public void updateNoteInfo(Note note);
    public List<NoteCategory> getAllNotes(Integer user_id, String type);
    public void deleteNotes(Integer id);
}
