#include "Calculate.h"
//#include "GaussRemove.h"
//#include "stdio.h"

double ee = 0.0000000001;
long MaxN = 1000;	//���ڵ���

void main()
{
	double e = 0;
	int n;	//��ʼ�ڵ����
	
	double * pd_Result = new double [1];		//u(xi)�Ľ��

	//�������η�,��ʼn = 32
	n = 32 ;
	do 
	{	
		n = n*2;
		if ( n > MaxN)
		{
			n = n/2;
			break;
		}		
		delete []pd_Result ;		
		pd_Result = FormulaTrapezia(n,e);
		
	} 
	while( e >= ee);

 	OutPutResult(pd_Result,n);
	cout<<"The number of FormulaTrapezia is "<<n<<"!"<<endl;	
	
	cout<<"The error of FormulaTrapezia is:";
	cout<<setiosflags(ios::scientific)<<setprecision(11)<<e<<endl;
	
	//��Simpson��,��ʼn = 32
	n = 32 ;
	do 
	{	
		n = n*2;
		if ( n > MaxN)
		{
		n = n/2;
		break;
		}		
		delete []pd_Result ;		
		pd_Result = FormulaSimpson(n,e);	
	} 
	while( e >= ee);


 	OutPutResult(pd_Result,n);
	cout<<"The number of FormulaSimpson is "<<n<<"!"<<endl;	
	
	cout<<"The error of FormulaSimpson is:";
	cout<<setiosflags(ios::scientific)<<setprecision(11)<<e<<endl;
	



	//Gauss���ַ�
	n = 7;
	
	pd_Result = FormulaGauss(n,e);

  	ShowVector(pd_Result,n);
	cout<<"The error of FormulaGauss is:";
	cout<<setiosflags(ios::scientific)<<setprecision(11)<<e<<endl;	
}

