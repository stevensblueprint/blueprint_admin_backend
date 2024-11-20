package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.npos.NPO;
import com.sitblueprint.admin.service.users.NPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/npos/")
public class NPOController {

    @Autowired
    NPOService npoService;

    @GetMapping("all")
    public List<NPO> getAllNPOs() { return npoService.getAllNPOs(); }

    @GetMapping("/{id}")
    public NPO getNPOById(@PathVariable("id") String npoId) { return npoService.getNPOById(Long.parseLong(npoId)); }

    @PostMapping
    public NPO createNPO(@RequestBody NPO npo) { return npoService.createNPO(npo); }

    @PutMapping
    public NPO updateNPO(@RequestBody NPO npo) { return npoService.updateNPO(npo); }

    @DeleteMapping
    public void deleteNPO(String npoId) { npoService.deleteNPOById(Long.parseLong(npoId)); }
}
