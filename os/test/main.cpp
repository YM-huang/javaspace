#include<windows.h>
#include <iostream>
#include <process.h>
#include <Tlhelp32.h>
#include <winbase.h>
#include <string.h>
using namespace std;

// 强制退出进程
int ExitProcess()
{
    HWND hWnd = FindWindow(NULL,"文档-写字板");
    if( hWnd == NULL )
    {
        printf("FindWindow 失败");
        return -1;
    }
    DWORD pid = 0;
    GetWindowThreadProcessId(hWnd,&pid);
    if( pid == 0)
    {
        printf("GetWindowThreadProcessId 失败");
        return -1;
    }
    HANDLE hNote = OpenProcess(PROCESS_ALL_ACCESS,FALSE,pid);
    if( hNote == NULL )
    {
        printf("OpenProcess 失败");
        return -1;
    }
    BOOL ret = TerminateProcess(hNote,-1);
    if( ret )
    {
        printf("成功退出");
    }else
    {
        printf("TerminateProcess 失败");
        return -1;
    }

    return 0;
}

void open(){
    STARTUPINFO startupInfo = { 0 };
    PROCESS_INFORMATION  processInformation = { 0 };
    /*打开Word应用程序*/
    BOOL bSuccess = CreateProcess(TEXT("C:\\Program Files\\Windows NT\\Accessories\\wordpad.exe"), NULL, NULL, NULL, FALSE, NULL, NULL, NULL, &startupInfo, &processInformation);

    if (bSuccess)
    {
        cout << "Process started..." << endl
             << "ProcessID: "
             << processInformation.dwProcessId << endl;
    }
    else
    {
        cout << "Can not start process!" << endl
             << "Error code: " << GetLastError();
    }
    system("pause");
}

int main()
{
    char a;
    while(TRUE)
    {
        cout<<"please input the command:";
        cin>>a;
        switch (a)
        {
            case '1':
                open();
                cout<<"command confirmed"<<endl;
                break;
            case '2':
                ExitProcess();
                cout<<"command confirmed"<<endl;
                break;
            case '3':
                exit(0);
                break;
            default:
                cout<<"wrong command"<<endl;
        }

    }
}
