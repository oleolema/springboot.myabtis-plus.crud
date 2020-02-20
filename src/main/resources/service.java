package #{#mainPackage}.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import #{#mainPackage}.bean.#{#beanName};
import java.util.List;

public interface #{#className} extends IService<#{#beanName}> {
    /**
     * 分页查询
     */
    IPage<#{#beanName}> pageList(QueryWrapper<#{#beanName}> wrapper, Integer pageNo, Integer pageSize);

    List<#{#beanName}> pageList(QueryWrapper<#{#beanName}> wrapper);
}