package com.yqh.mybaitsplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqh.mybaitsplus.bean.#{#beanName};
import com.yqh.mybaitsplus.service.#{#beanName}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class #{#beanName}Controller {

    @Autowired
    private #{#beanName}Service #{#beanNameCamel}Service;

    @GetMapping("/#{#beanNameCamel}")
    public IPage<#{#beanName}> #{#beanNameCamel}(#{#beanName} #{#beanNameCamel}, @RequestParam(value = "p", defaultValue = "0") int p, @RequestParam(value = "s", defaultValue = "-1") int s) {
        QueryWrapper<#{#beanName}> queryWrapper = new QueryWrapper<>(#{#beanNameCamel});
        return #{#beanNameCamel}Service.pageList(queryWrapper, p, s);
    }

    @GetMapping("/#{#beanNameCamel}/{id}")
    public #{#beanName} #{#beanNameCamel}ById(@PathVariable("id") String id) {
        return #{#beanNameCamel}Service.getById(id);
    }

    @PostMapping("/#{#beanNameCamel}")
    public boolean modify(#{#beanName} #{#beanNameCamel}) {
        return #{#beanNameCamel}Service.updateById(#{#beanNameCamel});
    }

    @PutMapping("/#{#beanNameCamel}")
    public #{#beanName} save(#{#beanName} #{#beanNameCamel}) {
        log.info(#{#beanNameCamel}.toString());
        if (#{#beanNameCamel}Service.save(#{#beanNameCamel})) {
            return #{#beanNameCamel};
        }
        return null;
    }

    @DeleteMapping("/#{#beanNameCamel}/{id}")
    public boolean remove(@PathVariable("id") String id) {
        return #{#beanNameCamel}Service.removeById(id);
    }

    @DeleteMapping("/#{#beanNameCamel}")
    public boolean remove(#{#beanName} #{#beanNameCamel}) {
        QueryWrapper<#{#beanName}> wrapper = new QueryWrapper<>(#{#beanNameCamel});
        return #{#beanNameCamel}Service.remove(wrapper);
    }


}