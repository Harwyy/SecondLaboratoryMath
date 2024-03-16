package Math.second.math.Comtroller;

import Math.second.math.Service.Five;
import Math.second.math.Service.Four;
import Math.second.math.Service.Six;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("api/v1/System")
public class SystemController {

    Four four;
    Five five;
    Six six;

    @Autowired
    public SystemController(Four four, Five five, Six six) {
        this.four = four;
        this.five = five;
        this.six = six;
    }

    @GetMapping("/solve")
    public ResponseEntity<HashMap<String, Object>> solve(@RequestParam String number,
                                                         @RequestParam String leftX, @RequestParam String rightX,
                                                         @RequestParam String leftY, @RequestParam String rightY,
                                                         @RequestParam String epsilon){
        Double LEFTX = Double.parseDouble(leftX);
        Double RIGHTX = Double.parseDouble(rightX);
        Double LEFTY = Double.parseDouble(leftY);
        Double RIGHTY = Double.parseDouble(rightY);
        Double EPSILON = Double.parseDouble(epsilon);

        ArrayList<Double> list = new ArrayList<>();

        switch (number){
            case "4":
                list = four.methodIteration(LEFTX, RIGHTX, LEFTY, RIGHTY, EPSILON);
                break;
            case "5":
                list = five.methodIteration(LEFTX, RIGHTX, LEFTY, RIGHTY, EPSILON);
                break;
            case "6":
                list = six.methodIteration(LEFTX, RIGHTX, LEFTY, RIGHTY, EPSILON);
                break;
        }
        return ResponseEntity.ok(parse(list));
    }

    private HashMap<String, Object> parse(ArrayList<Double> list){
        HashMap<String, Object> map = new HashMap<>();
        if (list.size() == 5){
            map.put("x", list.get(0));
            map.put("y", list.get(1));
            map.put("result_1", list.get(2));
            map.put("result_2", list.get(3));
            map.put("count_of_iteration", list.get(4));
            map.put("status", "success");
        } else {
            if (list.get(0) == Double.MAX_VALUE){
                map.put("status", "condition not fulfilled");
            } else {
                map.put("status", "not solve");
            }
        }
        return map;
    }
}
