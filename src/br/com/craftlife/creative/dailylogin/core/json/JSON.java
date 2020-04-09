package br.com.craftlife.creative.dailylogin.core.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.craftlife.creative.dailylogin.Main;
import br.com.craftlife.creative.dailylogin.core.MessagesManager;
import br.com.craftlife.creative.dailylogin.core.model.JogadorDAO;

public class JSON {

	private String pluginFolder = Main.getMain().pluginFolder;
	private final static FileConfiguration config = Main.getMain().getConfig();

	@SuppressWarnings("unchecked")
	public void writeJSON(String fileName, String subPath, String object, String value) {

		new Thread(new BukkitRunnable() {

			@Override
			public void run() {
				JSONObject main = new JSONObject();

				main.put(object, value);

				try {
					File file = new File(pluginFolder + File.separator + subPath + fileName + ".json");
					File filePath = new File(pluginFolder + File.separator + subPath);
					filePath.mkdirs();
					if (!file.exists()) {
						file.createNewFile();
					}
					try (FileWriter fileWriter = new FileWriter(file)) {
						fileWriter.write(main.toJSONString());
						fileWriter.flush(); 
					}
				} catch (Exception e) {
					Bukkit.getConsoleSender().sendMessage("§7Log || §4ERRO§7: Erro ao escrever arquivo §5"+fileName);
				}
			}
		}).start();
	}

	public void readJSON(String fileName, String subPath, String object, Player player, boolean prizes, CommandSender sender) {
		new Thread(new BukkitRunnable() {

			@Override
			public void run() {
				String var = null;
				try {
					JSONParser parser = new JSONParser();

					File file = new File(pluginFolder + File.separator + subPath + fileName + ".json");
					Object obj = parser.parse(new FileReader(file));

					JSONObject jsonObject = (JSONObject) obj;

					var = (String) jsonObject.get(object);

					Gson gson = new Gson();
					Type type = new TypeToken<Map<String, String>>(){}.getType();
					Map<String, String> map = gson.fromJson(var, type);

					JogadorDAO.getJogadorData(player, map);
					if (prizes) {
						JogadorDAO.checkDates(JogadorDAO.getJogador(player), player);
					} else {
						MessagesManager.checkMsgs(sender, JogadorDAO.getJogador(player));
					}
				} catch (NullPointerException e) {
					Bukkit.getConsoleSender().sendMessage("§7Log || §4ERRO§7: Resposta nula.");
				} catch (FileNotFoundException e) {
					if (prizes) {
						JogadorDAO.setFirstData(player);
						MessagesManager.firstLogin(player);
						JogadorDAO.givePrizes(player, config.getInt("prizes.day1.money"), config.getInt("prizes.day1.dust"), config.getInt("prizes.day1.box.qntd"), config.getInt("prizes.day1.box.lvl"));
					} else {
						sender.sendMessage("§4§lJogador não encontrado.");
					}
				} catch (Exception e) {
					Bukkit.getConsoleSender().sendMessage("§7Log || §4ERRO§7: Erro desconhecido ou não tratado.");
					e.printStackTrace();
				}
			}
		}).start();
	}
}