#include "GaussRemove.h"
#include <stdlib.h>
#include <stdio.h>

#include <fstream>
#include <string>
#include <sstream> 

using namespace std;  

#define BOOL int
#define FALSE 0
#define TRUE 1


//求函数g(x)的值
double Gfun(double x)
{
	double result = exp(4*x) + (exp(x+4)-exp(-(x+4))) / (x+4);
	return result;	
}


//用复化梯形求积公式解方程,e记录误差
double * FormulaTrapezia(int n, double& e)
{
	double * pd_MatrixA;	//系数矩阵
	double * pd_VectorB;	//常数向量
	double * pd_Result;		//u(xi)的向量
	double h = 2.0/n;
	n ++;		//实际上区间[a,b]上有n+1个点

	double * p_x = new double[n];
	
	
	pd_MatrixA = new double [n*n];
	pd_VectorB = new double [n];
	pd_Result = new double [n];
	

	for(int i = 0; i < n; i++)
	{
		p_x[i] = h*i - 1;
		pd_VectorB[i] = Gfun(p_x[i]);
	}
	
	for(i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			if ((j == 0) || (j == n-1))
			{
				pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * h/2;
			}
			else
			{
				pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * h;
			}
			
			if (i == j)
			{
				pd_MatrixA[i*n + j] = pd_MatrixA[i*n + j] + 1;
			}
		}
	}

	GaussRemove(pd_MatrixA,pd_VectorB,pd_Result,n);

	e = 0;
	double temp;	//可以检测单点误差
	for(i = 0; i < n; i++)
	{
		temp = exp(4*p_x[i]) - pd_Result[i];
		if ((i == 0) || (i == n-1))
		{
			e += (h/2) * pow((exp(4*p_x[i]) - pd_Result[i]),2);	
		}
		else
		{
			e += h * pow((exp(4*p_x[i]) - pd_Result[i]),2);	
		}		
	}	
	
	return pd_Result;
}

//用复化Simpson求积公式解方程,e记录误差
double * FormulaSimpson(int n, double& e)
{
	double * pd_MatrixA;	//系数矩阵
	double * pd_VectorB;	//常数向量
	double * pd_Result;		//u(xi)的向量

	double h = 2.0/n;
	n++;//实际上区间[a,b]上有n+1个点

	double * p_x = new double[n];
	
	
	pd_MatrixA = new double [n*n];
	pd_VectorB = new double [n];
	pd_Result = new double [n];
	
	
	
	for(int i = 0; i < n; i++)
	{
		p_x[i] = h*i - 1;
		pd_VectorB[i] = Gfun(p_x[i]);
	}
	
	for(i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			if ((j == 0) || (j == n-1))
			{
				pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * h/3;
			}
			else if (j%2 == 0)
			{
				pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * 2 * h/3;
			}
			else
			{
				pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * 4 * h/3;
			}
			
			if (i == j)
			{
				pd_MatrixA[i*n + j] = pd_MatrixA[i*n + j] + 1;
			}
		}
	}
	
	GaussRemove(pd_MatrixA,pd_VectorB,pd_Result,n);

	e = 0;
	double temp;	//可以检测单点误差
	for(i = 0; i < n; i++)
	{
		temp = exp(4*p_x[i]) - pd_Result[i];
		if ((i == 0) || (i == n-1))
		{
			e += (h/3) * pow((exp(4*p_x[i]) - pd_Result[i]),2);	
		}
		else if (i%2 == 0)
		{
			e += (h*2/3) * pow((exp(4*p_x[i]) - pd_Result[i]),2);	
		}
		else
		{
			e += (h*4/3) * pow((exp(4*p_x[i]) - pd_Result[i]),2);	
		}
		
	}	
	
	return pd_Result;
}

//用Gauss求积公式解方程,e记录误差
double * FormulaGauss(int n, double& e)
{
	double * pd_MatrixA;	//系数矩阵
	double * pd_VectorB;	//常数向量
	double * pd_Result;		//u(xi)的向量

	pd_MatrixA = new double [n*n];
	pd_VectorB = new double [n];
	pd_Result = new double [n];

	double * p_A = new double[n];
	double * p_x = new double[n];

	p_A[0] = 0.1294849662;
	p_A[6] = 0.1294849662;
	p_x[6] = 0.9491079123;	
	p_x[0] = -p_x[6];
	
	p_A[1] = 0.2797053915;
	p_A[5] = 0.2797053915;
	p_x[5] = 0.7415311856;
	p_x[1] = -p_x[5];
	
	p_A[2] = 0.3818300505;
	p_A[4] = 0.3818300505;
	p_x[4] = 0.4058451514;
	p_x[2] = -p_x[4];

	p_A[3] = 0.4179591837;
	p_x[3] = 0;

	for(int i = 0; i < n; i++)
	{
		pd_VectorB[i] = Gfun(p_x[i]);
	}

	for(i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			pd_MatrixA[i*n + j] = exp(p_x[i]*p_x[j]) * p_A[j];
			if (i == j)
			{
				pd_MatrixA[i*n + j] = pd_MatrixA[i*n + j] + 1;
			}
		}
	}

	GaussRemove(pd_MatrixA,pd_VectorB,pd_Result,n);
	
	e = 0;
	double temp;	//可以检测单点误差
	for(i = 0; i < n; i++)
	{
		temp = exp(4*p_x[i]) - pd_Result[i];
		e += p_A[i] * pow((exp(4*p_x[i]) - pd_Result[i]),2);
	}
	
	return pd_Result;
}

//将结果写入txt文件中
void OutPutResult(double * pd_Result, int n) 
{
	fstream MyFile;

	MyFile.open("1.txt",ios::in| ios::out | ios::app);	
	stringstream ss;
	
	char Ch = '\n';
	char Ch1 = '\t';
	char str[13];
	char strTitle[] = "The number of X is";
	int Num = 0;	//记录n的位数
	int temp = n;
	while (temp != 0)
	{
		temp = temp/10;
		Num++;
	}
	char *p_strNum = new char[Num];
	
	
	MyFile.write(strTitle,sizeof(strTitle));
	sprintf(p_strNum,"%d",n);
	
	MyFile.write(p_strNum,sizeof(p_strNum));
	MyFile.write(&Ch,sizeof(char));

	for(int i = 0; i <= n; i++)
	{
		sprintf(str,"%.11lf",-1+i*2.0/n); 
		MyFile.write(str,13);
		MyFile.write(&Ch1,sizeof(char));
		sprintf(str,"%.11lf",pd_Result[i]); 
		MyFile.write(str,13);		
		MyFile.write(&Ch,sizeof(char));
	}	
	MyFile.close();
	
}