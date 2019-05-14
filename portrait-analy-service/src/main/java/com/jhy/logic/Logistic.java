package com.jhy.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Java实现逻辑回归算法
 */
public class Logistic {
 
	public static void main(String[] args) {
        colicTest();
	}
 
	/**
	 */
	public static void LogisticTest() {
		// TODO Auto-generated method stub
		CreateDataSet dataSet = new CreateDataSet();
		dataSet = readFile("testSet.txt");
		ArrayList<Double> weights = new ArrayList<Double>();
		weights = gradAscent1(dataSet, dataSet.labels, 150);
		for (int i = 0; i < 3; i++) {
			System.out.println(weights.get(i));
		}
		System.out.println();
	}
 
	/**
	 * @param inX
	 * @param weights
	 * @return
	 */
	public static String classifyVector(ArrayList<String> inX, ArrayList<Double> weights) {
		ArrayList<Double> sum = new ArrayList<Double>();
		sum.clear();
		sum.add(0.0);
		for (int i = 0; i < inX.size(); i++) {
			sum.set(0, sum.get(0) + Double.parseDouble(inX.get(i)) * weights.get(i));
		}
		if (sigmoid(sum).get(0) > 0.5)
			return "1";
		else
			return "0";
 
	}
 
	/**
	 */
	public static void colicTest() {
		// 训练集
		CreateDataSet trainingSet = new CreateDataSet();
		// 测试集
		CreateDataSet testSet = new CreateDataSet();
		// 读取数据集（训练集、测试集）
		trainingSet = readFile("testTraining.txt");					// 23 445 34 1  45 56 67 0
		testSet = readFile("Test.txt");								// 23 445 34 1  45 56 67 0
		ArrayList<Double> weights = new ArrayList<Double>();							// 权值，每个维度都会有个最终的权值
		weights = gradAscent1(trainingSet, trainingSet.labels, 500);	// 获取训练后的权值
		int errorCount = 0;														// 判断准确率
		for (int i = 0; i < testSet.data.size(); i++) {
			if (!classifyVector(testSet.data.get(i), weights).equals(testSet.labels.get(i))) {
				errorCount++;
			}
			System.out.println(classifyVector(testSet.data.get(i), weights) + "," + testSet.labels.get(i));
		}
		System.out.println(1.0 * errorCount / testSet.data.size());
 
	}
 
	/**
	 * 预测函数
	 * @param inX
	 * @return
	 * @Description: [sigmod函数]
	 */
	public static ArrayList<Double> sigmoid(ArrayList<Double> inX) {
		ArrayList<Double> inXExp = new ArrayList<Double>();
		for (int i = 0; i < inX.size(); i++) {
			inXExp.add(1.0 / (1 + Math.exp(-inX.get(i))));
		}
		return inXExp;
	}
 
	/**
	 * 梯度下降法计算权值
	 * @param dataSet
	 * @param classLabels
	 * @param numberIter
	 * @return
	 */
	public static ArrayList<Double> gradAscent1(Matrix dataSet, ArrayList<String> classLabels, int numberIter) {
		int m = dataSet.data.size();			// 数据集一共多大
		int n = dataSet.data.get(0).size();		// 一条数据有多少个维度
		double alpha = 0.0;
		int randIndex = 0;
		ArrayList<Double> weights = new ArrayList<Double>();
		ArrayList<Double> weightstmp = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();
		ArrayList<Integer> dataIndex = new ArrayList<Integer>();
		ArrayList<Double> dataMatrixMulweights = new ArrayList<Double>();

		// 将每条数据的每个维度的权值赋为1
		for (int i = 0; i < n; i++) {
			weights.add(1.0);
			weightstmp.add(1.0);
		}
		dataMatrixMulweights.add(0.0);

		// 误差值
		double error = 0.0;

		// numberIter是训练次数
		for (int j = 0; j < numberIter; j++) {
			// 产生0到99的数组
			for (int p = 0; p < m; p++) {
				dataIndex.add(p);
			}
			// 进行每一次的训练
 
			for (int i = 0; i < m; i++) {
				alpha = 4 / (1.0 + i + j) + 0.0001;
				randIndex = (int) (Math.random() * dataIndex.size());	// 随机生成索引值
				dataIndex.remove(randIndex);
				double temp = 0.0;
				for (int k = 0; k < n; k++) {
					temp = temp + Double.parseDouble(dataSet.data.get(randIndex).get(k)) * weights.get(k);
				}
				dataMatrixMulweights.set(0, temp);

				// 计算预测值
				h = sigmoid(dataMatrixMulweights);

				// 计算原始值与预测值间的误差
				error = Double.parseDouble(classLabels.get(randIndex)) - h.get(0);
				double tempweight = 0.0;
				for (int p = 0; p < n; p++) {
					// 矩阵每列的值与误差相乘并设置权值
					tempweight = alpha * Double.parseDouble(dataSet.data.get(randIndex).get(p)) * error;
					weights.set(p, weights.get(p) + tempweight);
				}
			}
 
		}
		return weights;
	}
 
	/**
	 * @param dataSet
	 * @param classLabels
	 * @return
	 */
	public static ArrayList<Double> gradAscent0(Matrix dataSet, ArrayList<String> classLabels) {
		int m = dataSet.data.size();
		int n = dataSet.data.get(0).size();
		ArrayList<Double> weights = new ArrayList<Double>();
		ArrayList<Double> weightstmp = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();
		double error = 0.0;
		ArrayList<Double> dataMatrixMulweights = new ArrayList<Double>();
		double alpha = 0.01;
		for (int i = 0; i < n; i++) {
			weights.add(1.0);
			weightstmp.add(1.0);
		}
		h.add(0.0);
		double temp = 0.0;
		dataMatrixMulweights.add(0.0);
		for (int i = 0; i < m; i++) {
			temp = 0.0;
			for (int k = 0; k < n; k++) {
				temp = temp + Double.parseDouble(dataSet.data.get(i).get(k)) * weights.get(k);
			}
			dataMatrixMulweights.set(0, temp);
			h = sigmoid(dataMatrixMulweights);
			error = Double.parseDouble(classLabels.get(i)) - h.get(0);
			double tempweight = 0.0;
			for (int p = 0; p < n; p++) {
				tempweight = alpha * Double.parseDouble(dataSet.data.get(i).get(p)) * error;
				weights.set(p, weights.get(p) + tempweight);
			}
		}
		return weights;
	}
 
	/**
	 * @param dataSet
	 * @param classLabels
	 * @return
	 */
	public static ArrayList<Double> gradAscent(Matrix dataSet, ArrayList<String> classLabels) {
		int m = dataSet.data.size();
		int n = dataSet.data.get(0).size();
		ArrayList<Double> weights = new ArrayList<Double>();
		ArrayList<Double> weightstmp = new ArrayList<Double>();
		ArrayList<Double> h = new ArrayList<Double>();
		ArrayList<Double> error = new ArrayList<Double>();
		ArrayList<Double> dataMatrixMulweights = new ArrayList<Double>();
		double alpha = 0.001;
		int maxCycles = 500;
		for (int i = 0; i < n; i++) {
			weights.add(1.0);
			weightstmp.add(1.0);
		}
		for (int i = 0; i < m; i++) {
			h.add(0.0);
			error.add(0.0);
			dataMatrixMulweights.add(0.0);
		}
		double temp;
		for (int i = 0; i < maxCycles; i++) {
			for (int j = 0; j < m; j++) {
				temp = 0.0;
				for (int k = 0; k < n; k++) {
					temp = temp + Double.parseDouble(dataSet.data.get(j).get(k)) * weights.get(k);
				}
				dataMatrixMulweights.set(j, temp);
			}
			h = sigmoid(dataMatrixMulweights);
			for (int q = 0; q < m; q++) {
				error.set(q, Double.parseDouble(classLabels.get(q)) - h.get(q));
			}
			double tempweight = 0.0;
			for (int p = 0; p < n; p++) {
				tempweight = 0.0;
				for (int q = 0; q < m; q++) {
					tempweight = tempweight + alpha * Double.parseDouble(dataSet.data.get(q).get(p)) * error.get(q);
				}
				weights.set(p, weights.get(p) + tempweight);
			}
		}
		return weights;
	}

	public Logistic() {
		super();
	}

	/**
	 * @param fileName
	 *            读入的文件名
	 * @return
	 */
	public static CreateDataSet readFile(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		CreateDataSet dataSet = new CreateDataSet();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				String[] strArr = tempString.split("\t");
				ArrayList<String> as = new ArrayList<String>();
				as.add("1");
				for (int i = 0; i < strArr.length - 1; i++) {
					as.add(strArr[i]);
				}
				dataSet.data.add(as);
				dataSet.labels.add(strArr[strArr.length - 1]);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return dataSet;
	}
 
}
