package com.xy.community.mapper;

import com.xy.community.model.Question;

public interface QuestionExtMapper {
   int incView(Question record);
   int incComment(Question record);
}
