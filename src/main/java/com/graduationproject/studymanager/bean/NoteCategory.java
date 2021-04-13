package com.graduationproject.studymanager.bean;

import lombok.Data;
import java.util.List;

@Data
public class NoteCategory {
    private User user;
    private Integer id;
    private String category;
    private List<Note> notes;
}
