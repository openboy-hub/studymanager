package com.graduationproject.studymanager.mapper;

import com.graduationproject.studymanager.bean.Note;
import com.graduationproject.studymanager.bean.NoteCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
    public void setNewNote(Note note);
    public void updateNoteInfo(Note note);
    public List<NoteCategory> getAllNotes(Integer user_id, String type);
    public void deleteNotes(Integer id);
}
