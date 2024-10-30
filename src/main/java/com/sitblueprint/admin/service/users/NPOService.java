package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.npos.NPO;
import com.sitblueprint.admin.model.users.Team;

import java.util.List;

public interface NPOService {
    List<NPO> getAllNPOs();

    NPO getNPOById(Long id);

    NPO createNPO(NPO npo);

    NPO updateNPO(NPO npo);

    void deleteNPOById(Long id);
}