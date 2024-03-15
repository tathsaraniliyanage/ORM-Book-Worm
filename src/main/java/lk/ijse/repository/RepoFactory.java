package lk.ijse.repository;

import lk.ijse.repository.impl.*;
import org.modelmapper.ModelMapper;

/**
 * @author Praboda Thathsarani
 * @Project Lavish_Styloo
 * @Date 24/12/2023
 */
public class RepoFactory {
    private static RepoFactory repositoryFactory;

    private RepoFactory() {
    }

    public static RepoFactory getRepoFactory() {
        return repositoryFactory == null ? repositoryFactory = new RepoFactory() : repositoryFactory;
    }

    public SuperRepo getRepo(RepositoryTypes repositoryTypes) {
        switch (repositoryTypes) {
            case BOOK:return new BookRepositoryImpl();
            case USER:return  new UserRepositoryImpl();
            case BORROWING:return new BorrowingRepositoryImpl();
            case BRANCH:return new BranchRepositoryImpl();
            case BORROWING_DETAILS:return new BorrowingDetailRepositoryImpl();

            default:
                return null;
        }
    }

    public enum RepositoryTypes {
        BOOK,USER,BORROWING,BRANCH, BORROWING_DETAILS
    }
}
