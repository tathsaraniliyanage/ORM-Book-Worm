package lk.ijse.service;

import lk.ijse.dto.BranchDTO;


public interface BranchService extends CrudService<BranchDTO> {
    BranchDTO getBranch(Long id);

}
