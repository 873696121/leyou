package huhong.leyou.item.controller;

import ch.qos.logback.core.util.ContextUtil;
import huhong.leyou.common.pojo.PageResult;
import huhong.leyou.item.pojo.Brand;
import huhong.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ){
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if(CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Brand>> queryAll(){
        List<Brand> list =  this.brandService.queryAll();
        if(CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        brandService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("query/{id}")
    public ResponseEntity<Brand> queryById(@PathVariable("id") Integer id){
        Brand brand = brandService.queryById(id);
        if(brand == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }

    @PostMapping("update")
    public ResponseEntity<Void> update(Brand brand){
        this.brandService.update(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("save")
    public ResponseEntity<Void> save(Brand brand){
        if(brand == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        this.brandService.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
