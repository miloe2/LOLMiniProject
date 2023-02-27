package vo;

import java.sql.Date;

public class LolUserVO {

    private String id;
    private String pwd;
    private String email;
    private String nickname;
    private Date birth_day;
    private int ph_number;
    private int gold;
    private int buy_gold;
    private String rank;

    public LolUserVO(String id, String pwd, String email, String nickname, Date birth_day, int ph_number, int gold, int buy_gold, String rank) {
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.nickname = nickname;
        this.birth_day = birth_day;
        this.ph_number = ph_number;
        this.gold = gold;
        this.buy_gold = buy_gold;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public int getPh_number() {
        return ph_number;
    }

    public void setPh_number(int ph_number) {
        this.ph_number = ph_number;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getBuy_gold() {
        return buy_gold;
    }

    public void setBuy_gold(int buy_gold) {
        this.buy_gold = buy_gold;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


}

