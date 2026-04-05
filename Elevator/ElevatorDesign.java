import java.util.*;

enum Direction {
    UP, DOWN, IDLE
}

enum State {
    MOVING, IDLE
}

class Elevator {
    int id;
    int currentFloor;
    Direction direction;
    State state;
    TreeSet<Integer> upStops;
    TreeSet<Integer> downStops;

    Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.state = State.IDLE;
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>(Collections.reverseOrder());
    }

    void addStop(int floor) {
        if(floor > currentFloor) {
            upStops.add(floor);
            if (direction == Direction.IDLE) direction = Direction.UP;
        } else if(floor < currentFloor) {
            downStops.add(floor);
            if (direction == Direction.IDLE) direction = Direction.DOWN;
        }
    }

    void move() {
        if (upStops.isEmpty() && downStops.isEmpty()) {
            state = State.IDLE;
            direction = Direction.IDLE;
            return;
        }

        state = State.MOVING;

        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }

        System.out.println("Elevator " + id + " moved to floor " + currentFloor);

        if (upStops.contains(currentFloor)) {
            System.out.println("Stopping at floor " + currentFloor);
            upStops.remove(currentFloor);
        }

        if (downStops.contains(currentFloor)) {
            System.out.println("Stopping at floor " + currentFloor);
            downStops.remove(currentFloor);
        }

        if (direction == Direction.UP && upStops.isEmpty()) {
            if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
            } else {
                direction = Direction.IDLE;
            }
        }

        if (direction == Direction.DOWN && downStops.isEmpty()) {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
            } else {
                direction = Direction.IDLE;
            }
        }
    }

    int getElevatorId() {
        return id;
    }

    int getCurrentFloor() {
        return currentFloor;
    }

    Direction getDirection() {
        return direction;
    }
}

class Dispatcher {
    List<Elevator> elevators;

    Dispatcher() {
        elevators = new ArrayList<>();
    }

    void addElevator(Elevator e) {
        elevators.add(e);
    }

    Elevator selectElevator(int floor, Direction direction) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for(Elevator e: elevators) {
            if(e.getDirection() == direction) {
                    if ((direction == Direction.UP && e.getCurrentFloor() <= floor) ||
                    (direction == Direction.DOWN && e.getCurrentFloor() >= floor)) {

                    int distance = Math.abs(e.getCurrentFloor() - floor);

                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = e;
                    }
                }
            }
        }

        if (bestElevator == null) {
            for (Elevator e : elevators) {
                if (e.getDirection() == Direction.IDLE) {
                    int distance = Math.abs(e.getCurrentFloor() - floor);

                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = e;
                    }
                }
            }
        }

        if(bestElevator == null) {
            for(Elevator e: elevators) {
                int distance = Math.abs(e.getCurrentFloor() - floor);

                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = e;
                }
            }
        }

        return bestElevator;
    }
}

class ExternalRequest {
    int floor; 
    Direction direction;

    ExternalRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    int getFloor() {
        return floor;
    }

    Direction getDirection() {
        return direction;
    }
}

class ExternalDispatcher {
    Dispatcher dispatcher;

    ExternalDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void submitRequest(ExternalRequest request) {
        Elevator elevator = dispatcher.selectElevator(
            request.getFloor(),
            request.getDirection()
        );

        if (elevator == null) {
            System.out.println("No elevator available");
            return;
        }

        System.out.println("Request assigned to Elevator: " + elevator.getElevatorId());
        elevator.addStop(request.getFloor());
    }
}

class ExternalButton {
    int floor;
    Direction direction;
    ExternalDispatcher dispatcher;

    ExternalButton(int floor, Direction direction, ExternalDispatcher dispatcher) {
        this.floor = floor;
        this.direction = direction;
        this.dispatcher = dispatcher;
    }

    void pressButton() {
        ExternalRequest request = new ExternalRequest(floor, direction);
        dispatcher.submitRequest(request);
    }
}

class InternalDispatcher {
    Map<Integer, Elevator> elevatorMap;

    InternalDispatcher(List<Elevator> elevators) {
        elevatorMap = new HashMap<>();
        for (Elevator e : elevators) {
            elevatorMap.put(e.getElevatorId(), e);
        }
    }

    void submitRequest(int elevatorId, int floor) {
        Elevator elevator = elevatorMap.get(elevatorId);

        if (elevator == null) {
            System.out.println("Invalid elevator ID");
            return;
        }

        elevator.addStop(floor);
    }
}

class InternalButton {
     int floor;
     int elevatorId;
     InternalDispatcher dispatcher;

    InternalButton(int floor, int elevatorId, InternalDispatcher dispatcher) {
        this.floor = floor;
        this.elevatorId = elevatorId;
        this.dispatcher = dispatcher;
    }

    void pressButton() {
        dispatcher.submitRequest(elevatorId, floor);
    }
}

public class ElevatorDesign {
    public static void main(String[] args) {

        // Create elevators
        Elevator el1 = new Elevator(1, 0);
        Elevator el2 = new Elevator(2, 5);

        List<Elevator> elevators = new ArrayList<>();
        elevators.add(el1);
        elevators.add(el2);

        // Create dispatcher
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.elevators = elevators;

        // External flow
        ExternalDispatcher externalDispatcher = new ExternalDispatcher(dispatcher);

        ExternalButton button1 = new ExternalButton(3, Direction.UP, externalDispatcher);
        button1.pressButton();

        // Internal flow
        InternalDispatcher internalDispatcher = new InternalDispatcher(elevators);

        InternalButton ib1 = new InternalButton(5, 1, internalDispatcher); // elevator 1
        ib1.pressButton();

        // Simulate movement
        for (int i = 0; i < 5; i++) {
            el1.move();
            el2.move();

            System.out.println("Elevator 1 at floor: " + el1.getCurrentFloor());
            System.out.println("Elevator 2 at floor: " + el2.getCurrentFloor());
        }
    }
}