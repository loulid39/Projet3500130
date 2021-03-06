package com.mehellou.projet_3500130;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by carre on 24/01/18.
 */

public class CSVHandler {
	public enum fileName{CAPITAL, WORLD, BATIMENT};
	private Context context;


	public CSVHandler(Context current){
		super();
		this.context = current;
	}

	/*
	* nbValues: nombre de valeurs à renvoyer
	* range: sur combien de valeurs il faut random
	 */
	public int[] randomValues(int nbvalues, int nblines){
		final Random random = new Random();
		final Set<Integer> intSet = new HashSet<>();
		while (intSet.size() < nbvalues) {
			int rand = random.nextInt(nblines);
			if (rand > 1 && rand <= nblines){
				intSet.add(rand);
			}
		}
		final int[] ints = new int[intSet.size()];
		final Iterator<Integer> iter = intSet.iterator();
		for (int i = 0; iter.hasNext(); ++i) {
			ints[i] = iter.next();
		}
		Arrays.sort(ints);
		return ints;
	}

	/*
	* Retourne une liste de coordonnées
	* filename = le type enum du fichier à lire
	* nbvalues = nombre de valeurs à random
	* */
	public ArrayList getRandom(fileName f, int nbvalues){
		InputStream inputStream;
		switch (f) {
			case CAPITAL: inputStream = context.getResources().openRawResource(R.raw.capital);
				break;
			case WORLD: inputStream = context.getResources().openRawResource(R.raw.worldcities);
				break;
			case BATIMENT: inputStream = context.getResources().openRawResource(R.raw.batiments);
				break;
			default: return null;
		}

			/*les lignes sont comptées à partir de 1 et la première contient le nb de lignes*/
		int line = 1;
		int j= 0;
		ArrayList resultList = new ArrayList();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
				/* par convention, la première ligne contient le nombre de lignes total du fichier*/
			String csvLine =  reader.readLine();
			int[] randomLines = randomValues(nbvalues, Integer.parseInt(csvLine));

			for (int i=0; i<nbvalues; i++){
				while(line != randomLines[i]){
					csvLine = reader.readLine();
					line++;
				}
				String[] row = csvLine.split(",");
				float rowfloat[] = new float[2];
				rowfloat[0] = Float.parseFloat(row[0]);
				rowfloat[1] = Float.parseFloat(row[1]);
				resultList.add(rowfloat);
			}

		}
		catch (IOException ex) {
			throw new RuntimeException("Error in reading CSV file: "+ex);
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException e) {
				throw new RuntimeException("Error while closing input stream: "+e);
			}
		}
		return resultList;
	}

	public List get(fileName f){
		InputStream inputStream;
		switch (f) {
			case CAPITAL: inputStream = context.getResources().openRawResource(R.raw.worldcities);
				break;
			case WORLD: inputStream = context.getResources().openRawResource(R.raw.worldcities);
				break;
			default: return null;
		}

		List resultList = new ArrayList();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			String csvLine;
			while ((csvLine = reader.readLine()) != null) {
				String[] row = csvLine.split(",");
				float rowfloat[] = new float[2];
				rowfloat[0] = Float.parseFloat(row[0]);
				rowfloat[1] = Float.parseFloat(row[0]);
				resultList.add(rowfloat);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException("Error in reading CSV file: "+ex);
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException e) {
				throw new RuntimeException("Error while closing input stream: "+e);
			}
		}
		return resultList;
	}


}