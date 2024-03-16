package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.repository.RepoFactory;
import lk.ijse.repository.UserRepository;
import lk.ijse.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.List;


public class UserServiceImpl implements UserService {
    private final Session session = FactoryConfiguration.getInstance().getSession();
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository =
            (UserRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.USER);

    @Override
    public boolean save(UserDTO dto) {
        try {
            userRepository.setSession(session);
            Long save = userRepository.save(modelMapper.map(dto, User.class));
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            return userRepository.isExits(save).getId() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(UserDTO dto) {
        try {
            userRepository.setSession(session);
            User isExits = userRepository.isExits(dto.getId());
            if (isExits != null && isExits.getId() != null) {
                userRepository.update(modelMapper.map(dto, User.class));
                Transaction transaction = session.beginTransaction();
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public List<UserDTO> search(String text) {
        return null;
    }

    @Override
    public UserDTO getUser(Long id) {
        try {
            userRepository.setSession(session);
            return modelMapper.map(userRepository.isExits(id), UserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDTO auth(String password, String username) {
        try {
            userRepository.setSession(session);
            User auth = userRepository.auth(password, username);
            if (auth != null)
                return modelMapper.map(auth, UserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
