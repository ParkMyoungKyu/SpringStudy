package hello.hellospring.web;

import hello.hellospring.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public void logic(String testId) {
//        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id : " + testId);
    }
}
