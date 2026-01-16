// Smart Home System - Story Example
abstract class Device {
    protected String deviceName;
    protected boolean isOn;
    protected double powerConsumption;

    public Device(String name, double power) {
        this.deviceName = name;
        this.powerConsumption = power;
        this.isOn = false;
    }

    public void togglePower() {
        isOn = !isOn;
        System.out.println(deviceName + " is now " + (isOn ? "ON" : "OFF"));
    }

    public abstract void showStatus();

    public double calculateDailyConsumption(int hours) {
        return powerConsumption * hours;
    }
}

interface Controllable {
    void remoteControl();
    void autoMode();
}

class SmartLight extends Device implements Controllable {
    public SmartLight() {
        super("Smart Light", 0.05);
    }

    @Override
    public void showStatus() {
        System.out.println("Light is " + (isOn ? "Glowing" : "Off"));
    }

    @Override
    public void remoteControl() {
        System.out.println("Light controlled via remote");
    }

    @Override
    public void autoMode() {
        System.out.println("Auto-adjusting brightness based on surroundings");
    }
}

class SmartFan extends Device implements Controllable {
    public SmartFan() {
        super("Smart Fan", 0.1);
    }

    @Override
    public void showStatus() {
        System.out.println("Fan is " + (isOn ? "Running" : "Stopped"));
    }

    @Override
    public void remoteControl() {
        System.out.println("Fan speed controlled via remote");
    }

    @Override
    public void autoMode() {
        System.out.println("Auto-adjusting speed based on temperature");
    }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        System.out.println("=== Smart Home System Demo ===\n");
        
        SmartLight light = new SmartLight();
        SmartFan fan = new SmartFan();
        
        // Light operations
        System.out.println("1. Smart Light Operations:");
        light.togglePower();
        light.showStatus();
        light.remoteControl();
        light.autoMode();
        System.out.println("Daily consumption: " + light.calculateDailyConsumption(10) + " kWh\n");
        
        // Fan operations
        System.out.println("2. Smart Fan Operations:");
        fan.togglePower();
        fan.showStatus();
        fan.remoteControl();
        fan.autoMode();
        System.out.println("Daily consumption: " + fan.calculateDailyConsumption(10) + " kWh");
        
        // Abstract class vs Interface explanation
        System.out.println("\n=== Design Explanation ===");
        System.out.println("Abstract Class 'Device' provides:");
        System.out.println("- Common state (deviceName, isOn, powerConsumption)");
        System.out.println("- Concrete methods (togglePower, calculateDailyConsumption)");
        System.out.println("- Abstract method (showStatus)");
        
        System.out.println("\nInterface 'Controllable' provides:");
        System.out.println("- Common behavior contract (remoteControl, autoMode)");
        System.out.println("- Allows multiple implementation");
    }
}
