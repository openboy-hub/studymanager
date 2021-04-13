package com.graduationproject.studymanager.serviceImp;

import com.graduationproject.studymanager.bean.Note;
import com.graduationproject.studymanager.bean.NoteCategory;
import com.graduationproject.studymanager.mapper.NoteMapper;
import com.graduationproject.studymanager.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteMapper noteMapper;

    @Override
    public void setNewNote(Note note) {
        noteMapper.setNewNote(note);
    }

    @Override
    public void updateNoteInfo(Note note) {
        noteMapper.updateNoteInfo(note);
    }

    @Override
    public List<NoteCategory> getAllNotes(Integer user_id, String type) {
        return noteMapper.getAllNotes(user_id, type);
    }

    @Override
    public void deleteNotes(Integer id) {
        noteMapper.deleteNotes(id);
    }
}
