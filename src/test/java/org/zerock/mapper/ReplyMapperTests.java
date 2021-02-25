package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.domain.ReplyVO;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@Log4j
public class ReplyMapperTests {

    // 테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
//    private Long[] bnoArr = {3145745L, 3145744L, 3145743L, 3145742L, 3145741L};
    private Long[] bnoArr = {141L, 140L, 139L, 138L, 137L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Test
    public void testMapper() {
        log.info(mapper);
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1,10).forEach((i -> {
            ReplyVO vo = new ReplyVO();
            // 게시물의 번호
            vo.setBno(bnoArr[i % 5]).setReply("댓글 테스트 " + i).setReplyer("replyer" + i);
            mapper.insert(vo);
        }));
    }

    @Test
    public void testRead() {
        Long targetRno = 5L;
        ReplyVO vo = mapper.read(targetRno);
        log.info(vo);
    }

    @Test
    public void testDelete() {
        Long targetRno = 2L;
        mapper.delete(targetRno);
    }

    @Test
    public void testUpdate() {
        Long targetRno = 10L;
        ReplyVO vo = mapper.read(targetRno);
        vo.setReply("Update Reply ");
        int count = mapper.update(vo);
        log.info("UPDATE COUNT: " + count);
    }
}