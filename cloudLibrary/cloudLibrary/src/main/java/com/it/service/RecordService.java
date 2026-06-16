package com.it.service;

import com.it.pojo.Record;
import com.it.pojo.User;
import com.it.utils.PageResult;

public interface RecordService {
    int addRecord(Record record);
    PageResult searchRecords(Record record, User user, int pageNum, int pageSize);
}
