package hello;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, value="/testfile")
    public ResponseEntity greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println(name);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/html")
    public String testHtml(@RequestParam(value="name", defaultValue="World") String name) {
        return "testHtml.html";
    }

    @RequestMapping (value = "/image", method = RequestMethod.GET, produces= MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] sampleImage() throws IOException {
        System.out.println("In image mapping");
        InputStream in = getClass().getResourceAsStream("/image.jpg");
        return IOUtils.toByteArray(in);
    }

    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> defaultPath() {
        System.out.println("Unmapped request handling!");
        return new ResponseEntity<String>("Unmapped request", HttpStatus.OK);
    }

}
