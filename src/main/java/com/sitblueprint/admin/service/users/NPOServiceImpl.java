package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.npos.NPO;
import com.sitblueprint.admin.repository.users.NPORepository;

import java.util.List;

public class NPOServiceImpl implements NPOService {

    NPORepository npoRepository;

    public NPOServiceImpl(NPORepository npoRepository) {
        this.npoRepository = npoRepository;
    }

    @Override
    public List<NPO> getAllNPOs() {
        return npoRepository.findAll();
    }

    @Override
    public NPO getNPOById(Long id) {
        return npoRepository.findById(id).orElse(null);
    }

    public NPO createNPO(NPO npo) {

    }

    public NPO updateNPO(NPO npo) {

    }

    public void deleteNPOById(Long id) {

    }
}
