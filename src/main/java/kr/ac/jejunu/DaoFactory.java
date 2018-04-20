package kr.ac.jejunu;

public class DaoFactory {
    public ProductDao getDao() {
        return new ProductDao(connectionMaker());
    }

    private ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }
}
