package huhong.leyou.item.service;

import huhong.leyou.item.mapper.SpecGroupMapper;
import huhong.leyou.item.mapper.SpecParamMapper;
import huhong.leyou.item.pojo.SpecGroup;
import huhong.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;


    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> groups = this.groupMapper.select(specGroup);
        return groups;
    }

    public List<SpecParam> queryParams(Long gid) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        List<SpecParam> select = this.paramMapper.select(param);
        return select;
    }
}
