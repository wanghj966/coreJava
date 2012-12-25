#include "math.h"
#include "iostream.h"
#include <iomanip.h>

#define BOOL int
#define FALSE 0
#define TRUE 1

//��ʾ����
void ShowMatrix(double * P_Matrix,int M, int N)
{
	cout<<endl;
	for(int i = 0; i < M; i++)
	{
		for(int j = 0; j < N; j++)
		{
			
			cout<<P_Matrix[i*N+j]<<"   ";
			if (j == N-1)
			{
				cout<<endl;
			}
		}
	}	
}

//��ʾ����
void ShowVector(double * P_Vector,int M)
{
	cout<<endl;	
	for(int i = 0; i < M; i++)
	{
		cout<<setiosflags(ios::fixed)<<setprecision(11)<<P_Vector[i]<<endl;
	}	
}


//�Ծ������˳���˹��ȥ
BOOL GaussRe(double * P_Matrix,double * P_MatrixG,double * P_Vector, double * P_VectorG,int M, int N)
{
	double m,temp;	
	int i,j,k,l;
	int flag = 0;
	//����ԭʼ���������
	for(i = 0; i < M; i++)
	{
		P_VectorG[i] = P_Vector[i];
		for(j = 0; j < N; j++)
		{
			P_MatrixG[i*N+j] = P_Matrix[i*N+j];
		}
	}
	//��ʼ��ȥ��
	for(k = 0; k < M-1; k++)
	{
		temp = P_MatrixG[k];
		flag = k;
		//�ҳ���Ԫ��
		for(l = k; l < M; l++ )
		{
			if (fabs(P_MatrixG[l*N+k]) > fabs(temp)) 
			{
				temp = P_MatrixG[l*N+k];
				flag = l;
			}
		}
		//������
		if (flag != k)
		{
			for(l = k; l < N; l++)
			{
				//����ϵ������Ԫ��
				temp = P_MatrixG[flag*N+l];
				P_MatrixG[flag*N+l] = P_MatrixG[k*N+l];
				P_MatrixG[k*N+l] = temp;
			}			
			//��������Ԫ��
			temp = P_VectorG[flag];
			P_VectorG[flag] = P_VectorG[k];
			P_VectorG[k] = temp;
		}		
		for(i = k+1; i < M; i++)
		{			
			m = P_MatrixG[i*N+k]/P_MatrixG[k*N+k];
			P_MatrixG[i*N+k] = 0;	//��ȥ��Ԫ��Ϊ0			
			for(j = k+1; j < N; j++)
			{
				P_MatrixG[i*N+j] = P_MatrixG[i*N+j]-m*P_MatrixG[k*N+j];
			}
			P_VectorG[i]= P_VectorG[i] - m*P_VectorG[k];
		}
	}
	return TRUE;
}


//�ش����
BOOL Calculate(double * P_MatrixG,double * P_VectorG, double * P_VectorX,int M, int N)
{
	int j,k;
	double temp;

	P_VectorX[N-1] = P_VectorG[M-1]/P_MatrixG[(M-1)*N+N-1];
	for(k = N-2; k >= 0; k--)
	{
		temp = 0;
		for(j = k+1; j < N; j++)
			{
				temp += P_MatrixG[k*N+j]*P_VectorX[j];
			}
		P_VectorX[k] = (P_VectorG[k] - temp)/P_MatrixG[k*N+k];
	}	
	return TRUE;	
}


BOOL GaussRemove(double * pd_MatrixA,double* pd_VectorB,double * pd_Result, int n)
{
	double * pd_MatrixA_Gauss = new double[n*n];
	double * pd_VectorB_Gauss = new double[n];

	GaussRe(pd_MatrixA,pd_MatrixA_Gauss,pd_VectorB,pd_VectorB_Gauss,n,n);
	Calculate(pd_MatrixA_Gauss, pd_VectorB_Gauss, pd_Result, n, n);
	delete pd_MatrixA_Gauss;
	delete pd_VectorB_Gauss;
	return TRUE;	
}