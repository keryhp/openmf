package uk.ac.openmf.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Map<Integer, Character> characters = new HashMap<Integer, Character>();

	static {
		try {
			characters
					.put(1,
							new Character(
									1,
									"Totoro",
									false,
									new URL(
											"http://animeonly.org/albums/VISINAUJI/EGIO/fourth/Mon-Voisin-Totoro/normal_totoro_001.jpg")));
			characters
					.put(2,
							new Character(
									2,
									"Satsuki Kusakabe",
									true,
									new URL(
											"http://profile.ak.fbcdn.net/hprofile-ak-ash2/48980_1802552968_7286_n.jpg")));
			characters
					.put(4,
							new Character(
									4,
									"Therru",
									false,
									new URL(
											"http://28.media.tumblr.com/tumblr_lj4ctjKA8Y1qdvyqpo1_400.jpg")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/characters/{characterId}", method = RequestMethod.GET)
	@ResponseBody
	public Character findCharacter(@PathVariable int characterId) {
		return characters.get(characterId);

	}

}
