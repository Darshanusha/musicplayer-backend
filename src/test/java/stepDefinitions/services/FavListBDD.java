package stepDefinitions.services;

import com.music.musicplayer.musicplayer.entity.SongInfo;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FavListBDD {

    @ParameterType(".*, .*")
    public SongInfo songInfo(int id, String name){
        return new SongInfo(id,name);
    }

    @Given("List of favorite song\\(s)")
    public void listOfFavoriteSongS() {
    }

    @When("Add song {songInfo} to List of songs")
    public void addSongToListOfSongs(SongInfo songInfo) {
        System.out.println(songInfo.musicName);
    }

    @Then("List of favorite song\\(s) should contain {string}")
    public void listOfFavoriteSongSShouldContain(String arg0) {
    }
}
