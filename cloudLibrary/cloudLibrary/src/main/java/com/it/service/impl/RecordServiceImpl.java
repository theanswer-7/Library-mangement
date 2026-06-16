package com.it.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.it.dao.RecordMapper;
import com.it.pojo.Record;
import com.it.pojo.User;
import com.it.service.RecordService;
import com.it.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public int addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public PageResult searchRecords(Record record, User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (!"ADMIN".equals(user.getRole())) {
            record.setBorrower(user.getName());
        }
        Page<Record> page = recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
