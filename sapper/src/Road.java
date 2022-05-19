import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Road {
	private final int column;
	private final int rows;
	private final int bombs;
	private int[][] map;
	private char[][] table;
	public Road(int rows, int column, int bombs){
		this.column = column;
		this.rows = rows;
		this.bombs = bombs;
		map = new int[rows][column];
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < rows; j++) {
				map[i][j] = 0;
			}
		}
		table  = new char[rows][column];
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < rows; j++) {
				table[i][j] = '/';
			}
		}
		create();
	}

	private void create(){
		Random random = new Random();
		for (int i = 0; i < bombs; i++) {
			map[random.nextInt(column - 1)][random.nextInt(column - 1)] = -1;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < column; j++) {
				if (map[i][j] == -1){
					if (i - 1 >= 0 && map[i - 1][j] != -1)
						map[i - 1][j]++;
					if(j - 1 >= 0 && map[i][j - 1] != -1)
						map[i][j - 1]++;
					if(j - 1 >= 0 && i - 1 >= 0 && map[i - 1][j - 1] != -1)
						map[i - 1][j - 1]++;
					if (i + 1 < rows && map[i + 1][j] != -1)
						map[i + 1][j]++;
					if(j + 1 < column && map[i][j + 1] != -1)
						map[i][j + 1]++;
					if(j + 1 < column && i + 1 < rows && map[i + 1][j + 1] != -1)
						map[i + 1][j + 1]++;
					if(j + 1 < column && i - 1 >= 0 && map[i - 1][j + 1] != -1)
						map[i - 1][j + 1]++;
					if(j - 1 >= 0 && i + 1 < rows && map[i + 1][j - 1] != -1)
						map[i + 1][j - 1]++;
				}
			}
		}
	}

	public int[][] getMap(){
		return map;
	}

	public char[][] getTable(int i, int j) {
		//req(i, j);
		return table;
	}

	public void display(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(table[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	public void display_map(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(map[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	public boolean count(){
		Integer count = 0;
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < rows; j++) {
				if (table[i][j] == '/')
					count++;
			}
		}
		return count.equals(bombs);
	}

	public void req(int i, int j){
		if (map[i][j] != 0 || i >= column || j >= column || i < 0 || j < 0){
			if(i < column && j < column)
				table[i][j] = (char)(map[i][j] + '0');
		}
		else {
			if (i + 1 < column) {
				if (map[i + 1][j] == 0 && table[i+1][j] == '/') {
					table[i + 1][j] = '.';
					req(i + 1, j);
				} else
					if (table[i+1][j] == '/')
						table[i + 1][j] = (char) (map[i + 1][j] + '0');
			}
			if (i - 1 >= 0) {
				if (map[i - 1][j] == 0 && table[i-1][j] == '/') {
					table[i - 1][j] = '.';
					req(i - 1, j);
				} else
					if (table[i-1][j] == '/')
						table[i - 1][j] = (char) (map[i - 1][j] + '0');
			}
			if (j + 1 < column) {
				if (map[i][j + 1] == 0 && table[i][j+1] == '/') {
					table[i][j + 1] = '.';
					req(i, j + 1);
				} else
					if (table[i][j+1] == '/')
						table[i][j + 1] = (char) (map[i][j + 1] + '0');
			}
			if (j - 1 >= 0) {
				if (map[i][j - 1] == 0 && table[i][j-1] == '/') {
					table[i][j - 1] = '.';
					req(i, j - 1);
				} else
					if (table[i][j-1] == '/')
						table[i][j - 1] = (char) (map[i][j - 1] + '0');
			}
		}
	}
}
