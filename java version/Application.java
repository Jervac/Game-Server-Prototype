import Visionire.Configurations;
import Visionire.Const;
import Visionire.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.yaml.snakeyaml.Yaml;

public class Application {

	public static void main(String[] arguments) throws IOException {

		Yaml yaml = new Yaml();
		try (InputStream in = Files.newInputStream(Paths.get("./Resources/application.properties"))) {
			Configurations settings = yaml.loadAs(in, Configurations.class);
			System.out.println(settings.toString());

			Server server = new Server(Const.PORT);

			if (server.initialise()) server.construct();
			else server.deconstruct();
		}
	}
}