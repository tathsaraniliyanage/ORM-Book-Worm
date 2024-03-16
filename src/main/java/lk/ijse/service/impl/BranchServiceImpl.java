package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.service.BranchService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;


public class BranchServiceImpl implements BranchService {
    private final Session session = FactoryConfiguration.getInstance().getSession();
    private final ModelMapper modelMapper = new ModelMapper();
    private final BranchRepository branchRepository =
            (BranchRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BRANCH);

    @Override
    public boolean save(BranchDTO dto) {
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            Long save = branchRepository.save(modelMapper.map(dto, Branch.class));
            transaction.commit();
            return save != null;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BranchDTO dto) {
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            branchRepository.update(modelMapper.map(dto, Branch.class));
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            branchRepository.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BranchDTO> findAll() {
        try {
            branchRepository.setSession(session);
            return modelMapper.map(branchRepository.findAll(), new TypeToken<List<BranchDTO>>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BranchDTO> search(String text) {
        return null;
    }

    @Override
    public BranchDTO getBranch(Long id) {
        try {
            branchRepository.setSession(session);
            Branch branch = branchRepository.isExits(id);
            return modelMapper.map(branch, BranchDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
