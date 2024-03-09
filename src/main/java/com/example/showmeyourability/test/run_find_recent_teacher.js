import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
    vus: 50, // 동시에 실행될 가상 사용자(VU)의 수
    duration: '30s', // 테스트 지속 시간
};

export default function () {
    // 랜덤 페이지 번호 생성, 예를 들어 0에서 99 사이
    let response = http.get(`http://localhost:13022/api/teacher/recentTeacher`);
    check(response, {
        'is status 200': (r) => r.status === 200,
    });
    sleep(1); // 각 VU 사이에 휴식 시간 설정
}
