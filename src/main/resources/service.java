package #{#packageName};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqh.mybaitsplus.bean.#{#beanName};

public interface #{#className} extends IService<#{#beanName}> {
    /**
     * 分页查询
     */
    public IPage<#{#beanName}> pageList(QueryWrapper<#{#beanName}> wrapper, Integer pageNo, Integer pageSize);
}