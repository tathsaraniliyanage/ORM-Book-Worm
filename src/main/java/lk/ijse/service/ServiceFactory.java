package lk.ijse.service;


import lk.ijse.repository.impl.BorrowingDetailRepositoryImpl;
import lk.ijse.repository.impl.BorrowingRepositoryImpl;
import lk.ijse.repository.impl.BranchRepositoryImpl;
import lk.ijse.repository.impl.UserRepositoryImpl;
import lk.ijse.service.impl.*;

/**
 * @author Praboda Thathsarani
 * @Project Lavish_Styloo
 * @Date 24/12/2023
 */
public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getBoFactory() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public SuperService getService(ServiceTypes serviceTypes) {
        switch (serviceTypes) {
            case BOOK:  return new BookServiceImpl();
            case USER:return  new UserServiceImpl();
            case BORROWING:return new BorrowingServiceImpl();
            case BRANCH:return new BranchServiceImpl();
            case BORROWING_DETAILS:return new BorrowingDetailServiceImpl();

            default:
                return null;
        }
    }

    public enum ServiceTypes {
        BOOK,USER,BORROWING,BRANCH,BORROWING_DETAILS

    }
}
