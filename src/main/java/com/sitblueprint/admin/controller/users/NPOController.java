package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.npos.NPO;
import com.sitblueprint.admin.service.users.NPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/npos/")
public class NPOController {

    @Autowired
    NPOService npoService;

    public List<NPO> getAllNPOs() {}

    public NPO getNPOById() {}

    public NPO createNPO() {}

    public void deleteNPO() {}
}
