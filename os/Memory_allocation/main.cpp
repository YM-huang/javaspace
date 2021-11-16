#include<iostream>
#include<algorithm>
#include<windows.h>
#include<string>
#include<vector>
#define MIN 5
using namespace std;

struct free_table{
    int address;
    int size;
    int flag;//空闲时为0,分配时为1
};
struct used_table{
    string process;//作业名
    int address;
    int size;
    int flag;
};
free_table a[1]={0,100,0};
vector<free_table> vec_free(a,a+1);//空闲表初始化
vector<used_table> vec_used;//分配表
vector<used_table> vec;//内存

vector<free_table>::iterator it_f;//空闲表指针
vector<used_table>::iterator it_u;//分配表指针

void Output_free(vector<free_table> vec_free);//输出空闲表
void Output_used(vector<used_table> vec_used);//输出分配表
void Output(vector<used_table> vec);//输出内存分配

void Input1();//进程分配输入：最佳
void Input2();//进程分配输入：首次
void Input3();//进程分配输入：循环首次
void findsize(vector<free_table> &vec_free,vector<used_table> &vec_used,int size,string process);//最优适应算法分配
void recycle();//内存回收输入
void Refindsize(vector<free_table> &vec_free,vector<used_table> &vec_used,string process);//内存回收算法
void Memory(vector<free_table> &vec_free,vector<used_table> &vec_used,vector<used_table> &vec);//查看总内存分配
void menu();
bool cmp(free_table &a,free_table &b){//用于排序的比较
    return a.address<b.address;
}
bool cmp1(used_table &a,used_table &b){//用于排序的比较
    return a.address<b.address;
}
int main(){
    menu();
}
void menu(){
    int chose;
    cout<<"\t\t\t"<<"请选择您要进行的操作"<<endl;
    cout<<"\t\t\t"<<"退出运行------------0"<<endl;
    cout<<"\t\t\t"<<"进程分配：首次------------1"<<endl;
    cout<<"\t\t\t"<<"进程分配：循环首次------------2"<<endl;
    cout<<"\t\t\t"<<"进程分配：最佳------------3"<<endl;
    cout<<"\t\t\t"<<"进程回收------------4"<<endl;
    cout<<"\t\t\t"<<"查看空闲表----------5"<<endl;
    cout<<"\t\t\t"<<"查看分配表----------6"<<endl;
    cout<<"\t\t\t"<<"查看内存分配--------7"<<endl;
    cout<<"请输入要操作编号"<<endl;
    cin>>chose;
    switch(chose){
        case 0:
            exit(1);
            break;
        case 1:
            Input2();
            system("pause");
            system("cls");
            menu();
            break;
        case 2:
            Input3();
            system("pause");
            system("cls");
            menu();
            break;
        case 3:
            Input1();
            system("pause");
            system("cls");
            menu();
            break;
        case 4:
            recycle();
            system("pause");
            system("cls");
            menu();
            break;
        case 5:
            Output_free(vec_free);
            system("pause");
            system("cls");
            menu();
            break;
        case 6:
            Output_used(vec_used);
            system("pause");
            system("cls");
            menu();
            break;
        case 7:
            Output(vec);
            system("pause");
            system("cls");
            menu();
            break;
        default:
            cout<<"请输入正确的编号"<<endl;
            system("cls");
            menu();
            break;
    }
}
void Output_free(vector<free_table> vec_free){
    cout<<"输出空闲表:"<<endl;
    cout<<"起始地址"<<"	"<<"空间大小"<<"	"<<"标志位"<<endl;
    for(it_f=vec_free.begin();it_f!=vec_free.end();it_f++){
        cout<<it_f->address<<"\t\t"<<it_f->size<<"\t\t"<<it_f->flag<<endl;
    }
}
void Output_used(vector<used_table> vec_used){
    cout<<"输出分配表:"<<endl;
    cout<<"作业名称"<<"	"<<"起始地址"<<"	"<<"空间大小"<<"	"<<"标志位"<<endl;
    for(it_u=vec_used.begin();it_u!=vec_used.end();it_u++){
        cout<<it_u->process<<"\t\t"<<it_u->address<<"\t\t"<<it_u->size<<"\t\t"<<it_u->flag<<endl;
    }
}
void Output(vector<used_table> vec){
    Memory(vec_free,vec_used,vec);
    cout<<"输出内存分配:"<<endl;
    cout<<"作业名称"<<"	"<<"起始地址"<<"	"<<"空间大小"<<"	"<<"标志位"<<endl;
    for(it_u=vec.begin();it_u!=vec.end();it_u++){
        cout<<it_u->process<<"\t\t"<<it_u->address<<"\t\t"<<it_u->size<<"\t\t"<<it_u->flag<<endl;
    }
}
//最佳
void Input1(){
    int num;
    string pro;//输入的进程名
    int size;//输入的进程申请空间大小
    cout<<"请输入待分配进程的个数"<<endl;
    cin>>num;
    for(int i=0;i<num;i++){
        cout<<"请输入待分配进程名称，空间大小"<<endl;
        cin>>pro>>size;
        findsize(vec_free,vec_used,size,pro);
        sort(vec_used.begin(),vec_used.end(),cmp1);
    }
}

//首次
void Input2(){
    int num;
    string pro;//输入的进程名
    int size;//输入的进程申请空间大小
    cout<<"请输入待分配进程的个数"<<endl;
    cin>>num;
    for(int i=0;i<num;i++){
        cout<<"请输入待分配进程名称，空间大小"<<endl;
        cin>>pro>>size;
        findsize(vec_free,vec_used,size,pro);
    }
}

//循环首次
void Input3(){
    int num;
    string pro;//输入的进程名
    int size;//输入的进程申请空间大小
    cout<<"请输入待分配进程的个数"<<endl;
    cin>>num;
    for(int i=0;i<num;i++){
        cout<<"请输入待分配进程名称，空间大小"<<endl;
        cin>>pro>>size;
        findsize(vec_free,vec_used,size,pro);
    }
}

void findsize(vector<free_table> &vec_free,vector<used_table> &vec_used,int size,string process){
    int flag=0;//若第二次遍历也未找到空间分配，表明没有空间为其分配
    int tag=0;//用于标识第一次循环没有找到合适的分配空间
    for(it_f=vec_free.begin();it_f!=vec_free.end();){
        int space=it_f->size-size;
        if(space>MIN||space<0){
            it_f++;
        }
        else{//刚好有小于MIN空间进行分配
            tag=1;
            used_table record;
            record.process=process;
            record.size=it_f->size;
            record.address=it_f->address;
            record.flag=1;
            vec_free.erase(it_f);//从空闲表中删除该块
            vec_used.push_back(record);//向分配表中加入该块
            break;
        }
    }
    if(tag==0){//循环一次没有找到地址对他进行分配
        vector<free_table>::iterator it_t;//临时指针
        for(it_t=vec_free.begin(),it_f=vec_free.begin();it_f!=vec_free.end();it_t++){
            int space=it_f->size-size;
            if(space>0){//按照需要进行分割
                flag=1;
                used_table record;
                record.process=process;
                record.size=size;
                record.address=it_f->address;
                record.flag=1;
                vec_used.push_back(record);
                it_f->size=space;
                it_f->address=it_f->address+size;
                it_f->flag=0;
                break;
            }
            else{
                it_f++;
            }
        }
    }
    if(flag==0&&tag==0){//第一轮和第二轮都没有对其进行空间分配
        cout<<"空间已满！"<<endl;
    }
}
void recycle(){//回收
    int num;
    string pro;
    cout<<"请输入要回收的进程个数"<<endl;
    cin>>num;
    for(int i=0;i<num;i++){
        cout<<"请输入要回收的进程名"<<endl;
        cin>>pro;
        Refindsize(vec_free,vec_used,pro);
    }
}
void Refindsize(vector<free_table> &vec_free,vector<used_table> &vec_used,string process){
    vector<free_table>::iterator it;
    int num=vec_used.size();
    int flag=0;
    int tag=0;
    for(it_u=vec_used.begin();it_u!=vec_used.end();){
        flag++;
        if(it_u->process==process){
            for(it_f=vec_free.begin();it_f!=vec_free.end();){
                it=it_f+1;
                //回收区与前一个相邻，又与后一个相邻
                if(it_f->address+it_f->size==it_u->address&&it_u->address+it_u->size==it->address){
                    cout<<"回收区与前一个相邻，又与后一个相邻"<<endl;
                    it_f->size=it_f->size+it_u->size+it->size;//将三个空闲区合并，删除后一个空闲区
                    vec_free.erase(it);
                    vec_used.erase(it_u);
                    tag=1;
                    break;
                }
                    //回收区与前一个相邻
                else if(it_f->address+it_f->size==it_u->address){
                    cout<<"回收区与前一个相邻"<<endl;
                    it_f->size=it_f->size+it_u->size;//起址不变，修改空闲区变大
                    vec_used.erase(it_u);
                    tag=1;
                    break;
                }
                    //回收区与后一个相邻
                else if(it_f->address==it_u->address+it_u->size){
                    cout<<"回收区与后一个相邻"<<endl;
                    it_f->address=it_u->address;//修改起址，修改空闲区
                    it_f->size=it_u->size+it_f->size;
                    vec_used.erase(it_u);
                    tag=1;
                    break;
                }
                    //回收区没有与之相邻
                else{
                    it_f++;
                }
            }
            if(tag==0){
                cout<<"回收区没有与之相邻 "<<endl;
                free_table record;
                record.size=it_u->size;
                record.address=it_u->address;
                record.flag=0;
                vec_free.push_back(record);
                sort(vec_free.begin(),vec_free.end(),cmp);
                vec_used.erase(it_u);
            }
        }
        else{
            it_u++;
        }
    }
    if(flag>num){//遍历一遍没有找到
        cout<<"没有找到该进程名"<<endl;
    }
}
void Memory(vector<free_table> &vec_free,vector<used_table> &vec_used,vector<used_table> &vec){
    if(vec_free.size()==0){//全部分配完
        it_u=vec_used.begin();
        while(it_u!=vec_used.end()){
            used_table record;
            record.size=it_u->size;
            record.address=it_u->address;
            record.flag=0;
            record.process=it_u->process;
            vec.push_back(record);
            it_u++;
        }
    }
    else if(vec_used.size()==0){//全部没有分配
        it_f=vec_free.begin();
        while(it_f!=vec_free.end()){
            used_table record;
            record.size=it_f->size;
            record.address=it_f->address;
            record.flag=0;
            record.process="#";
            vec.push_back(record);
            it_f++;
        }
    }
    else{
        for(it_f=vec_free.begin(),it_u=vec_used.begin();it_f!=vec_free.end()&&it_u!=vec_used.end();){
            if(it_f->address<it_u->address){
                used_table record;
                record.size=it_f->size;
                record.address=it_f->address;
                record.flag=0;
                record.process="#";//表示内存空间为空,没有分配作业
                vec.push_back(record);
                it_f++;
            }
            else{
                used_table record;
                record.size=it_u->size;
                record.address=it_u->address;
                record.flag=1;
                record.process=it_u->process;
                vec.push_back(record);
                it_u++;
            }
        }
        while(it_f==vec_free.end()&&it_u!=vec_used.end()){
            used_table record;
            record.size=it_u->size;
            record.address=it_u->address;
            record.flag=1;
            record.process=it_u->process;
            vec.push_back(record);
            it_u++;
        }
        while(it_f!=vec_free.end()&&it_u==vec_used.end()){
            used_table record;
            record.size=it_f->size;
            record.address=it_f->address;
            record.flag=0;
            record.process="#";
            vec.push_back(record);
            it_f++;
        }
    }
}