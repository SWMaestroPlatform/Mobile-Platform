package soma.iot.sympathyhome.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import soma.iot.sympathyhome.R;

/**
 * Created by seojunkyo on 15. 8. 17..
 */
//클래스의 이름은 다른 이름이어도 상관없지만 상속만 BaseAdapter를 잘 받으면 됩니다.
public class ReminderDateAdapter extends BaseAdapter {

    ArrayList<ReminderDate> datas;
    LayoutInflater inflater;

    public ReminderDateAdapter(LayoutInflater inflater, ArrayList<ReminderDate> datas) {
        // TODO Auto-generated constructor stub

        //객체를 생성하면서 전달받은 datas(MemberData 객체배열)를 멤버변수로 전달
        //아래의 다른 멤버 메소드에서 사용하기 위해서.멤버변수로 참조값 전달
        this.datas= datas;

        //list_row.xml 파일을 View 객체로 생성(inflating) 해주는 역할의 객체
        //이 MemberDataAdapter 클래스를 객체로 만들어내는 클래스에서 LayoutInflater 객체 전달해주야 함.
        //이번 예제어세는 MainActivity.java에서 전달.
        //xml 종이의 글씨를 부풀려서 객체로 메모리에 만들어 낸다고 해서 '부풀리다(inflate)'라는 표현 사용
        this.inflater= inflater;
    }

    //ListView에서 이 MemberDataAdapter 객체에게 요청하는 값으로서
    //MemberDataAdapter 객체가 만들어낼 View의 개수를 리턴하는 메소드
    //ListView에 나열되는 목록의 개수를 의미함.
    //이 예제에서는 datas라는 ArrayList의 개수를 지칭함.
    //특별한 경우가 아니라면 보통 datas의 size 를 리턴함.
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    //MemberDataAdapter의 특정 위치(position)에 해당하는 Data를 리턴하는 메소드
    //이 예제에서는 datas의 특정위치에 해당하는  MemberData 객체를 리턴.
    //ListView의 position은 가장 위에 목록부터(0,1,2,3...)으로 지정됨.
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
    }

    //특정 위치(position)의 data(MemberData)을 지칭하는 아이디를 리턴하는 메소드
    //특별한 경우가 아니라면 보통은 data의 위치를 아이디로 사용하므로
    //전달받은 position를 그대로 리턴함.
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //이 커스텀 Adapter 클래스 설게에서 가장 중요한 메소드로서
    //ListView에서 한 항목의 View 모양과 값을 설정하는 메소드
    //AdapterView의 일종인 ListView는 설정된 Adapter(이 예제에서는 MemberDataAdapter)에게
    //대량의 데이터들(datas : MemberData객체 배열)을 보여주기에 적절한 View로 만들고
    //해당 데이터의 값으로 만들어 내는 핵심 메소드로서 ListView를 위에 만든 getCount()메소드의 리턴값만큼
    //getView를 요구하여  목록에 나열함.
    //즉, 이 메소드의 리턴값인 View 가 ListView의 한 항목을 의미합니다.
    //우리는 이 리턴될 View의 모양을 res폴더>>layout폴더>>list_row.xml 파일을 만들어 설계합니다.
    //첫번째 파라미터 position : ListView에 놓여질 목록의 위치.
    //두번째 파라미터 convertview : 리턴될 View로서 List의 한 함목의 View 객체(자세한 건 아래에 소개)
    //세번째 파라미터 parent : 이 Adapter 객체가 설정된 AdapterView객체(이 예제에서는 ListView)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        //크게 getView의 안에서는 2가지 작업이 이루어 집니다.
        //1. ListView의 목록 하나의 모양을 담당하는 View 객체를 만들어 내는 'New View'
        //2. 만들어진 View에 해당 Data를 연결하는 'Bind View'

        //1.New View
        //지문의 한계상 자세히는 설명하기 어려우니 세부사항은 다른 포스트를 참고하시기 바랍니다.
        //가장 먼저 new View를 하기 위해서 convertView 객체가 null인지 확인합니다.
        //null을 확인하는 이유는 자원 재활용때문인데..
        //짧게 설명하자면.. ListView에서 보여줄 목록이 만약 100개라면...
        //데이터의 양이 많아 분명 동시에 보여질 수 있는 목록의 개수를 정해져 있을 겁니다.
        //그래서 이전 예제에서 보았듯이 ListView는 개수가 많으면 자동으로 Scroll되도록 되어 있지요.
        //여기서 조금 생각해보시면 간단한데요.. 한번에 만약 5개 밖에 못보여진다면...
        //굳이 나머지 95개의 View를 미리 만들 필요가 없겠죠.. 어차피 한번에 보여지는게 5~6개 수준이라면..
        //그 정보의 View만 만들어서 안에 데이터만 바꾸면 메모리를 아낄 수 있다고 생각한 겁니다.
        //여기 전달되어 체크되고 있는 converView 객체가 재활용할 View가 있으면 null이 아닌값을 가지고
        //있다고 보시면 되고 converView가 null이면 새로 만들어야 한다고 보시면 됩니다.
        if( convertView==null){
            //null이라면 재활용할 View가 없으므로 새로운 객체 생성
            //View의 모양은 res폴더>>layout폴더>>list.xml 파일로 객체를 생성
            //xml로 선언된 레이아웃(layout)파일을 객체로 부풀려주는 LayoutInflater 객체 활용.
            convertView= inflater.inflate(R.layout.reminder_listitem, null);
        }

        //2. Bind View
        //재활용을 하든 새로 만들었든 이제 converView는 View객체 상태임.
        //이 convertView로부터 데이터를 입력할 위젯들 참조하기.
        //이름을 표시하는 TextView, 국적을 표시하는 TextView, 국기이미지를 표시하는 ImageView
        //convertView로 부터 findViewById()를 하시는 것에 주의하세요.
        TextView reminder_contents= (TextView)convertView.findViewById(R.id.listitem_reminder);
        TextView reminder_date= (TextView)convertView.findViewById(R.id.listitem_date);

        //현재 position( getView()메소드의 첫번재 파라미터 )번째의 Data를 위 해당 View들에 연결..
        reminder_contents.setText( datas.get(position).getSchedule() );
        reminder_date.setText( datas.get(position).getDate() );
        //설정이 끝난 convertView객체 리턴(ListView에서 목록 하나.)
        return convertView;
    }

}
