package #{#mainPackage}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import #{#mainPackage}.bean.#{#beanName};
import #{#mainPackage}.mapper.#{#beanName}Mapper;
import #{#mainPackage}.service.#{#beanName}Service;
import org.springframework.stereotype.Service;

@Service
public class #{#className} extends ServiceImpl<#{#beanName}Mapper, #{#beanName}> implements #{#beanName}Service{
    @Override
    public IPage<#{#beanName}> pageList(QueryWrapper<#{#beanName}> wrapper, Integer pageNo, Integer pageSize) {
        IPage<#{#beanName}> page = new Page<>(pageNo, pageSize);
        return baseMapper.selectPage(page, wrapper);
    }
}