package hello;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class TwitterController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());

        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        ArrayList<TwitterProfile> allFriends = friends;
        while (friends.hasNext()) {
            friends = twitter.friendOperations().getFriendsInCursor(friends.getNextCursor());
            allFriends.addAll(friends);
        }
        model.addAttribute("friends", allFriends);

        CursoredList<TwitterProfile> followers = twitter.friendOperations().getFollowers();
        ArrayList<TwitterProfile> allFollowers = followers;
        while (followers.hasNext()) {
            followers = twitter.friendOperations().getFollowersInCursor(followers.getNextCursor());
            allFollowers.addAll(followers);
        }
        model.addAttribute("followers", allFollowers);

        return "hello";
    }

}