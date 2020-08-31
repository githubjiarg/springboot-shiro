package com.spring.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("system")
public class SystemController {

    /**
     *  导入
     * @return
     */
    @RequestMapping("import")
    @RequiresPermissions("system:import")
    public Map<String,String> importExcel(){
        Map<String,String> map = new HashMap<String,String>(3);
        log.info("SystemController-import");
        return map;
    }

    /**
     *  删除
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions("system:delete")
    public Map<String,String> delete(){
        Map<String,String> map = new HashMap<String,String>(3);
        log.info("SystemController-delete");
        return map;
    }

    /**
     *  修改
     * @return
     */
    @RequestMapping("update")
    @RequiresPermissions("system:update")
    public Map<String,String> update(){
        Map<String,String> map = new HashMap<String,String>(3);
        log.info("SystemController-update");
        return map;
    }

    /**
     *  查询
     * @return
     */
    @RequestMapping("select")
    @RequiresPermissions("system:select")
    public Map<String,String> select(){
        Map<String,String> map = new HashMap<String,String>(3);
        log.info("SystemController-select");
        return map;
    }

}
