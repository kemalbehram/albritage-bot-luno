package org.example.local;

import com.google.gson.Gson;
import org.example.domain.enums.ExchangeEnums;
import org.example.domain.enums.ExchangeStates;
import org.example.domain.models.MainStateModel;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

public class DataStorage implements Serializable {
    public String playerName;

    public static void saveMainState (MainStateModel mainState) {
        try {
            FileOutputStream fos = new FileOutputStream("mainState.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            DataStorage dStorage = new DataStorage();
            dStorage.playerName = new Gson().toJson(mainState);

            oos.writeObject(dStorage);
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MainStateModel getMainState () {
        MainStateModel state = new MainStateModel("","", new BigDecimal("0.0"), new MainStateModel.ExchangeData.CoinData.Blockchain("","","",new BigDecimal("100.0"),100.0), ExchangeEnums.BINANCE,ExchangeEnums.BINANCE,"","","", ExchangeStates.FAILED,ExchangeStates.FAILED,ExchangeStates.FAILED, Map.of());

        try {
            FileInputStream fis = new FileInputStream("mainState.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois  = new ObjectInputStream(bis);

            DataStorage dStorage = (DataStorage) ois.readObject();

            state = new Gson().fromJson(dStorage.playerName, MainStateModel.class);

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return state;
    }
}
