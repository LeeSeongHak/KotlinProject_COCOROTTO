package Seong.Hak.Lee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 랜덤으로 번호 생성 카드의 클릭 이벤트 리스너
        randomCard.setOnClickListener {

            // ResultActivity를 시작하는 Intent 생성
            val intent = Intent(this, ResultActivity::class.java)

            // intent의 결과 데이터를 전달한다.
            // int의 리스트를 전달하므로 putIntegerArrayListExtra를 사용한다.
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getShuffleLottoNumbers()))

            // ResultActivity를 시작하는 Intent를 만들고 startActivity로 실행
            startActivity(intent)
        }

        // 별자리로 번호 생성 카드의 클릭 이벤트 리스너
        constellationCard.setOnClickListener {
            // ConstellationActivity를 시작하는 Intent를 만들고 startActivity로 실행
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        // 이름으로 번호 생성 카드의 클릭 이벤트 리스너
        nameCard.setOnClickListener {
            // NameActivity를 시작하는 Intent를 만들고 startActivity로 실행
            startActivity(Intent(this, NameActivity::class.java))
        }
    }

    fun getRandomLottoNumber(): Int {
        // Random.nextInt는 0 ~ 전달받은 파라미터 값 미만의 번호를 생성
        // ex) Random().nextInt(10)은 0 ~ 9 까지의 무작위 수를 반환
        // 1 ~ 45 까지의 번호를 생성하려면 파라미터의 45를 넣고 결과값의 1을 더한다.
        return Random().nextInt(45) + 1
    }

    /**
     * 랜덤으로 추출하여 6개의 로또 번호를 만드는 함수
     */
    fun getRandomLottoNumbers(): MutableList<Int>{
        // 무작위로 생성된 로또 번호를 저장할 가변 리스트 생성
        val lottoNumbers = mutableListOf<Int>()

        // 6번 반복하는 for 문
        for(i in 1..6){
            // 랜덤한 번호를 임시로 저장할 변수를 생성
            var number = 0
            do {
                // 랜덤한 번호를 추출해 number 변수를 저장
                number = getRandomLottoNumber()

                // lottoNumber에 number 변수의 값이 없을 때까지 반복
            } while (lottoNumbers.contains(number))

            // 이미 뽑은 리스트에 없는 번호가 나올 때까지 반복했으므로 중복이 없는 상태
            // 추출된 번호를 뽑은 리스트에 추가
            lottoNumbers.add(number)
        }

        return lottoNumbers
    }

    /**
     * 더 쉬운 중복 방지방법! : shuffle을 사용해 로또 번호 생성
     */
    fun getShuffleLottoNumbers(): MutableList<Int> {
        // 1 ~ 45 번에 로또 번호를 저장할 리스트 생성
        val list = mutableListOf<Int>()

        // 1 ~ 45 까지 for문을 돌면서 리스트에 로또 번호 저장
        for(number in 1..45){
            list.add(number)
        }

        // 리스트를 무작위로 섞는다.
        list.shuffle()

        //리스트를 앞에서부터 순서대로 6개를 짤라 결과 반환
        return list.subList(0, 6)
    }
}