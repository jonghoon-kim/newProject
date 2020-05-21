package data;

import data.base.IntEntityDao;
import entities.Betting;
import entities.Match;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BettingDao extends IntEntityDao<Betting> {
        //region singleton
            private BettingDao() {
            }

            private static BettingDao _instance;

            public static BettingDao getInstance(){
                if (_instance == null)
                    _instance = new BettingDao();

                return _instance;
            }

            //endregion

    @Override
    protected String getByKeyQuery() { return "select * from betting where bettingNo = ?"; }

    public String getByMatchTimeQuery() { return "select * from betting where matchTime = ?"; }

    public String getByBettingMoneyQuery() { return "select * from betting where bettingMoney = ?"; }

    public String getByBettingTeamQuery() { return "select * from betting where bettingTeam = ?"; }

    public String getByAllocationMoney() { return "select * from betting where allocationMoney = ?"; }

    public String getById() { return "select * from betting where id = ?"; }

    @Override
    protected String deleteByKeyQuery() {

        return "delete betting where bettingNo = ?";
    }

    @SneakyThrows
    @Override
    protected Betting readEntity(ResultSet result) {
        Betting entity = new Betting();

        entity.setBettingNo(result.getInt(1));
        entity.setMatchTime(result.getString(2)); // ->long
        entity.setBettingMoney(result.getInt(3));
        entity.setBettingTeam(result.getString("Ulsan"));
        entity.setBettingNo(result.getInt(5));
        return entity;
    }

    @Override
    protected String getCountQuery() {
        return "select count(*) from betting";
    }

    @Override
    protected String getAllQuery() {
        return "select * from betting";
    }

    @SneakyThrows
    public ArrayList<Betting> getByMatchTime(String matchTime) {
        //language=TSQL
        String query = getByMatchTimeQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, matchTime);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Betting> getByBettingMoney(int bettingMoney) {
        //language=TSQL
        String query = getByBettingMoneyQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, bettingMoney);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Betting> getByBettingTeam(String bettingTeam) {
        //language=TSQL
        String query = getByBettingTeamQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, bettingTeam);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Betting> getByAllocationMoney(int allocationMoney) {
        //language=TSQL
        String query = getByAllocationMoney();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, allocationMoney);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Betting> getById(String id) {
        //language=TSQL
        String query = getById();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, id);
            }
        });
    }

    @SneakyThrows
    public boolean insert(Betting entity) {
        //language=TSQL
        String query = "insert into Betting values (?, ?, ?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getBettingNo());
                statement.setString(2, entity.getMatchTime()); // -> long
                statement.setInt(3, entity.getBettingMoney());
                statement.setString(4, entity.getBettingTeam());
                statement.setInt(5, 0);
                statement.setString(6, entity.getId());
            }
        });
    }

    @SneakyThrows
    public boolean update(Betting entity) {
        //language=TSQL
        String query = "update Betting set matchTime = ?, bettingMoney = ?, bettingTeam = ?," +
                " allocationMoney = ?, id = ? where matchTime = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getBettingNo());
                statement.setString(2, entity.getMatchTime()); // -> long
                statement.setInt(3, entity.getBettingMoney());
                statement.setString(4, entity.getBettingTeam());
                statement.setInt(5, entity.getAllocationMoney());
                statement.setString(6, entity.getId());


            }
        });
    }
}
