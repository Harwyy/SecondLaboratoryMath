package Math.second.math.Comtroller;

import Math.second.math.Service.One;
import Math.second.math.Service.Three;
import Math.second.math.Service.Two;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/notSystem")
public class NotSystemController {

    One one;
    Two two;
    Three three;

    @Autowired
    public NotSystemController(One one, Two two, Three three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    @GetMapping("/solve")
    public ResponseEntity<HashMap<String, Object>> solve(@RequestParam String method, @RequestParam String number,
                                                         @RequestParam String a, @RequestParam String b, @RequestParam String epsilon){
        ArrayList<Double> list = new ArrayList<>();
        double A = Double.parseDouble(a);
        double B = Double.parseDouble(b);
        double EPSILON = Double.parseDouble(epsilon);
        switch (method){
            case "Newton":
                switch (number){
                    case "1":
                        list = one.methodNewton(A, B, EPSILON);
                        break;
                    case "2":
                        list = two.methodNewton(A, B, EPSILON);
                        break;
                    case "3":
                        list = three.methodNewton(A, B, EPSILON);
                        break;
                }
            case "Chord":
                switch (number){
                    case "1":
                        list = one.methodChord(A, B, EPSILON);
                        break;
                    case "2":
                        list = two.methodChord(A, B, EPSILON);
                        break;
                    case "3":
                        list = three.methodChord(A, B, EPSILON);
                        break;
                }
            case "Iteration":
                switch (number){
                    case "1":
                        list = one.methodOfIteration(A, B, EPSILON);
                        break;
                    case "2":
                        list = two.methodOfIteration(A, B, EPSILON);
                        break;
                    case "3":
                        list = three.methodOfIteration(A, B, EPSILON);
                        break;
                }
        }
        return ResponseEntity.ok(parse(list));
    }

    private HashMap<String, Object> parse(ArrayList<Double> list){
        HashMap<String, Object> map = new HashMap<>();
        if (list.size() == 3){
            map.put("x", list.get(0));
            map.put("result", list.get(1));
            map.put("count_of_iteration", list.get(2));
            map.put("status", "success");
        } else {
            if (list.get(0) == Double.MIN_VALUE){
                map.put("status", "not monotony");
            } else if (list.get(0) == 0){
                map.put("status", "not solve");
            } else {
                map.put("status", "condition not fulfilled");
            }
        }
        return map;
    }
}
