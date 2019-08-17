package com.ryan.hello.schedule;

import com.ryan.hello.cache.HotTagCache;
import com.ryan.hello.mapper.QuestionMapper;
import com.ryan.hello.model.Question;
import com.ryan.hello.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000*60*60*3)

    public void hotTagSchedule() {
        int offsize = 0;
        int limit = 20;
        log.info("The time is start {}", new Date());
        List<Question> list = new ArrayList<>();
        Map<String, Integer> priorities =new HashMap<>();
        while (offsize == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offsize, limit));

            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offsize += limit;
        }


        hotTagCache.updateTags(priorities);

        log.info("The time is stop {}", new Date());
    }
}
