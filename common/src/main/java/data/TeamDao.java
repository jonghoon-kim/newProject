package data;

import dao.base.StringKeyEntityDao;
import data.base.StringEntityDao;
import entities.Match;
import entities.Team;
import helpers.ConnectionString;
import lombok.SneakyThrows;
import util.ExitStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TeamDao extends StringEntityDao<Team> {
    //region singleton
        private TeamDao() {
            ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.5;database=Betting;user=sa;password=3512");
        }

        private static TeamDao _instance;

        public static TeamDao getInstance(){
            if (_instance == null)
                _instance = new TeamDao();

            return _instance;
        }
        //endregion

    @lombok.SneakyThrows
    @Override
    protected Team readEntity(ResultSet resultSet) {
            Team entity = new Team();

            entity.setTeam(resultSet.getString(1));
            entity.setWin(resultSet.getInt(2));
            entity.setLose(resultSet.getInt(3));
            entity.setHomeGround(resultSet.getString(4));

            return entity;
    }

    @Override
    protected String getCountQuery() {
            //language=TSQL
            return "Select count(*) from Team";
    }

    @Override
    protected String getAllQuery() {
            //language=TSQL
        return "select * from Team";
    }

    @Override
    protected String getByKeyQuery() {
            //language=TSQL
        return "select * from Team where Team = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
            //language=TSQL
        return "delete Team where Team = ?";
    }

    @Override
    public boolean insert(Team entity) {
            //language=TSQL
        String query = "insert into Team values (?, ?, ?, ?)";

        return execute(query, new data.ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setString(1, entity.getTeam()); // id 넣는게 맞는지 볼 것
                preparedStatement.setInt(2, entity.getWin());
                preparedStatement.setInt(3, entity.getLose());
                preparedStatement.setString(4, entity.getHomeGround());
            }
        });
    }

    @Override
    public boolean update(Team entity) {
            //language=TSQL
        String query = "update Team set Win = ?, Lose = ?, HomeGround = ? where Team = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setInt(1, entity.getWin());
                preparedStatement.setInt(2, entity.getLose());
                preparedStatement.setString(3, entity.getHomeGround());
                preparedStatement.setString(4, entity.getTeam());
            }
        });
   }
   public Team selectTeam(ExitStatus exit, Match match){
       Team teamBean = new Team();
       String str = String.format("1. %s 2. %s",match.getHomeTeam(),match.getAwayTeam());
       System.out.println(str);
       System.out.println("베팅할 팀의 번호를 입력하세요.("+exit.ESCAPE + " 를 입력하면 베팅 취소");
       Scanner sc = new Scanner(System.in);
       int inputValue=0;
       inputValue = sc.nextInt();
       switch (inputValue){
           case -2:
               exit.setEscapeStatus();
               return null;
           case 1:
               teamBean = TeamDao.getInstance().getByKey(match.getHomeTeam());
               return teamBean;

           case 2:
               teamBean = TeamDao.getInstance().getByKey(match.getAwayTeam());
               return teamBean;
           default:
               return null;

       }

   }
}
