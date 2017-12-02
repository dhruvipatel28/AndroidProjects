package dhruvipatel.c0719320_cricketplayerrank.Model;

/**
 * Created by macstudent on 2017-12-01.
 */

public class Players
{

    int id;
    String name;
    String gender;
    String birthdate;
    String category;
    String teamCountry;
    int testMatch;
    int oneDayMatch;
    int noOfCatch;
    int noOfRuns;
    int noOfWickets;
    int noOfStuming;
    int totalPoints;

    public Players() {

    }



    public Players(int id, String name, String gender, String birthrate, String category, String teamCountry,int testMatch, int oneDayMatch, int noOfCatch, int noOfRuns, int noOfWickets, int noOfStuming) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthrate;
        this.category = category;
        this.teamCountry = teamCountry;
        this.testMatch = testMatch;
        this.oneDayMatch = oneDayMatch;
        this.noOfCatch = noOfCatch;
        this.noOfRuns = noOfRuns;
        this.noOfWickets = noOfWickets;
        this.noOfStuming = noOfStuming;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeam() {
        return teamCountry;
    }

    public void setTeam(String team) {
        this.teamCountry = team;
    }

    public int getTestMatch() {
        return testMatch;
    }

    public void setTestMatch(int testMatch) {
        this.testMatch = testMatch;
    }

    public int getOneDayMatch() {
        return oneDayMatch;
    }

    public void setOneDayMatch(int oneDayMatch) {
        this.oneDayMatch = oneDayMatch;
    }

    public int getNoOfCatch() {
        return noOfCatch;
    }

    public void setNoOfCatch(int noOfCatch) {
        this.noOfCatch = noOfCatch;
    }

    public int getNoOfRuns() {
        return noOfRuns;
    }

    public void setNoOfRuns(int noOfRuns) {
        this.noOfRuns = noOfRuns;
    }

    public int getNoOfWickets() {
        return noOfWickets;
    }

    public void setNoOfWickets(int noOfWickets) {
        this.noOfWickets = noOfWickets;
    }

    public int getNoOfStuming() {
        return noOfStuming;
    }

    public void setNoOfStuming(int noOfStuming) {
        this.noOfStuming = noOfStuming;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
